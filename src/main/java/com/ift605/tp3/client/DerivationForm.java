package com.ift605.tp3.client;

import com.ift605.tp3.client.parsers.EquationParser;
import com.ift605.tp3.constants.ClientConstants;
import com.ift605.tp3.jade.agents.client.ClientAgent;
import jade.gui.GuiEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udes.ds.agent.AbstractEquation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Michaël on 11/16/2015.
 */
public class DerivationForm extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(ClientAgent.class);

    private JTextArea txtEquation;
    private JButton derivateButton;
    private JTextArea txtOutput;
    private JTextField txtParameter;
    private JTextField txtDerivateResult;
    private JTextField txtFunctionResult;
    private JPanel MainPanel;

    private final ClientAgent m_agent;
    private final EquationParser m_parser;
    private AbstractEquation m_lastEquation = null;
    private AbstractEquation m_lastDerivate = null;

    public DerivationForm(ClientAgent agent) {
        m_agent = agent;
        m_parser = new EquationParser();

        setContentPane(MainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GuiEvent ge = new GuiEvent(this,ClientConstants.SHUTDOWN);
                agent.postGuiEvent(ge);
                System.exit(0);
            }
        });

        derivateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractEquation parsedEquation = (AbstractEquation) m_parser.ParseEquation(txtEquation.getText());
                m_lastEquation = parsedEquation;

                logger.info("Parsed equation:" + parsedEquation.getUserReadableString());

                GuiEvent ge = new GuiEvent(this, ClientConstants.DERIVATE);
                ge.addParameter(parsedEquation);
                agent.postGuiEvent(ge);
            }
        });
    }

    public void appendOutput(String message){
        txtOutput.append(message + "\r\n");
    }

    public void refreshResults() {
        double param = 0;

        if (m_lastEquation == null)
            return;

        try {
            param = Double.parseDouble(txtParameter.getText());
        } catch(NumberFormatException ex) {
            txtDerivateResult.setText("");
            txtFunctionResult.setText("");
            return;
        }

        txtFunctionResult.setText(Double.toString(m_lastEquation.getFunctionValue(param)));
        txtDerivateResult.setText(Double.toString(m_lastDerivate.getFunctionValue(param)));
    }

    public void receiveDerivation(AbstractEquation equation){
        EventQueue.invokeLater(() -> {

            appendOutput("Derivation of " + m_lastEquation.getUserReadableString() + " = " + equation.getUserReadableString());
            m_lastDerivate = equation;
            refreshResults();

        });
    }

    public void logMessage(String message){
        EventQueue.invokeLater(() -> {
            appendOutput(message);
        });
    }
}
