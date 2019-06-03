import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;


public class Forma1 extends JFrame {
    private JTable table1;
    private JButton delButton;
    private JButton editButton;
    private JButton addButton;
    private JPanel jok;

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        jok = new JPanel();
        jok.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        editButton = new JButton();
        editButton.setText("Edit");
        jok.add(editButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        delButton = new JButton();
        delButton.setText("Del");
        jok.add(delButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        table1 = new JTable();
        jok.add(table1, new GridConstraints(0, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        addButton = new JButton();
        addButton.setText("Add");
        jok.add(addButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return jok;
    }

    public Forma1() {
        setTitle("DataBase form");
        setContentPane(jok);
        setVisible(true);
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    private JTable loadTableData() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Product code");
        columnNames.add("Product");
        columnNames.add("Price");
        columnNames.add("Date of delivery");
        columnNames.add("Date of sale");
        columnNames.add("Amount");
        Vector<ArrayList<String>> rows = new Vector<>();
        rows.addElement(new DatabaseConnection().getIntegerData("product_code"));
        rows.addElement(new DatabaseConnection().getProducts());
        rows.addElement(new DatabaseConnection().getIntegerData("price"));
        rows.addElement(new DatabaseConnection().getDatesOfDelivery());
        rows.addElement(new DatabaseConnection().getDatesOfSale());
        rows.addElement(new DatabaseConnection().getIntegerData("amount"));
        JTable tab = new JTable(new Vector<>(), columnNames);
        DefaultTableModel tableModel = (DefaultTableModel) tab.getModel();
        for(int i = 0; i < rows.get(0).size(); i++) {
            Vector<String> row = new Vector<>();
            for (int j = 0; j < rows.size(); j++) {
                row.add(rows.get(j).get(i));
            }
            tableModel.addRow(row);
        }
        tab.setFillsViewportHeight(true);
        return tab;
    }
    public static void main(String[] args) {
        new Forma1();
    }

    private void createUIComponents() {

        table1 = loadTableData();
    }
}