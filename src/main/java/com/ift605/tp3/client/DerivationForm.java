package com.ift605.tp3.client;

import com.ift605.tp3.client.parsers.EquationParser;
import udes.ds.agent.AbstractEquation;
import udes.ds.agent.Equation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Michaël on 11/16/2015.
 */
public class DerivationForm {
    private JTextArea txtEquation;
    private JButton derivateButton;
    private JTextArea txtOutput;
    private JTextField txtParameter;
    private JTextField txtDerivateResult;
    private JTextField txtFunctionResult;

    private final EquationParser m_parser;
    private AbstractEquation m_lastEquation = null;
    private AbstractEquation m_lastDerivate = null;

    public DerivationForm() {
        m_parser = new EquationParser();

        derivateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractEquation parsedEquation = (AbstractEquation) m_parser.ParseEquation(txtEquation.getText());
                AbstractEquation eq = null;//m_manager.Derivate((Equation)parsedEquation);
                if (eq == null) {
                    AppendOutput("Error while derivating equation.");
                } else {
                    AppendOutput("Derivation of " + parsedEquation.getUserReadableString() + " = " + eq.getUserReadableString());
                    m_lastDerivate = eq;
                    m_lastEquation = parsedEquation;
                    RefreshResults();
                }
            }
        });
    }

    public void AppendOutput(String message){
        txtOutput.append(message + "\r\n");
    }

    public void RefreshResults() {
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
}
