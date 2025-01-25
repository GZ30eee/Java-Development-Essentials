import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CurrencyConverter {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Enhanced Currency Converter");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Currency exchange rates
        HashMap<String, Double> exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0); // Base currency
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("INR", 74.5);
        exchangeRates.put("GBP", 0.75);
        exchangeRates.put("JPY", 109.6);

        // Labels and inputs
        JLabel labelAmount = new JLabel("Amount:");
        labelAmount.setBounds(50, 50, 100, 30);
        frame.add(labelAmount);

        JTextField textAmount = new JTextField();
        textAmount.setBounds(150, 50, 200, 30);
        frame.add(textAmount);

        JLabel labelFrom = new JLabel("From:");
        labelFrom.setBounds(50, 100, 100, 30);
        frame.add(labelFrom);

        JComboBox<String> fromCurrency = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        fromCurrency.setBounds(150, 100, 200, 30);
        frame.add(fromCurrency);

        JLabel labelTo = new JLabel("To:");
        labelTo.setBounds(50, 150, 100, 30);
        frame.add(labelTo);

        JComboBox<String> toCurrency = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        toCurrency.setBounds(150, 150, 200, 30);
        frame.add(toCurrency);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(150, 200, 100, 30);
        frame.add(convertButton);

        JLabel resultLabel = new JLabel("Result: ");
        resultLabel.setBounds(150, 250, 300, 30);
        frame.add(resultLabel);

        // Conversion logic
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(textAmount.getText());
                    String from = fromCurrency.getSelectedItem().toString();
                    String to = toCurrency.getSelectedItem().toString();

                    if (!exchangeRates.containsKey(from) || !exchangeRates.containsKey(to)) {
                        throw new Exception("Invalid currency selection.");
                    }

                    double rateFrom = exchangeRates.get(from);
                    double rateTo = exchangeRates.get(to);
                    double convertedAmount = amount * (rateTo / rateFrom);

                    resultLabel.setText("Result: " + String.format("%.2f", convertedAmount) + " " + to);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "An error occurred: " + ex.getMessage());
                }
            }
        });

        // Show the frame
        frame.setVisible(true);
    }
}
