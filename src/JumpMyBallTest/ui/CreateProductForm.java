package JumpMyBallApp.ui;

import JumpMyBallApp.entity.ProductEntity;
import JumpMyBallApp.manager.ProductEntityManager;
import JumpMyBallApp.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

public class CreateProductForm extends BaseForm {
    private JPanel mainPanel;
    private JTextField productTypeTextField;
    private JTextField titleTextField;
    private JSpinner articleSpinner;
    private JTextField descriptionTextField;
    private JTextField imageTextField;
    private JSpinner productionPersonCountSpinner;
    private JSpinner productionWorkshopNumberSpinner;
    private JSpinner minCostForAgentSpinner;
    private JButton addButton;
    private JButton backButton;

    public CreateProductForm() {
        super(800,600);
        setContentPane(mainPanel);
        initButtons();
        setVisible(true);
    }
    private void initButtons(){
        backButton.addActionListener(e -> {
            dispose();
            new MainForm();
        });
        addButton.addActionListener(e -> {
            String title = titleTextField.getText();
            if (title.isEmpty() || title.length()>100){
                JOptionPane.showMessageDialog(this,"Наименование не введено или слишеом длинное","Ошибка",JOptionPane.ERROR_MESSAGE);
                return;
            }
            String productType = productTypeTextField.getText();
            if (productType.isEmpty() || productType.length()>100){
                JOptionPane.showMessageDialog(this,"Тип не введён или слишеом длинное","Ошибка",JOptionPane.ERROR_MESSAGE);
                return;
            }
            int article = (int) articleSpinner.getValue();
            String description = descriptionTextField.getText();
            String image =imageTextField.getText();
            int productionPersonCount = (int) productionPersonCountSpinner.getValue();
            int productionWorkshopNumber = (int) productionWorkshopNumberSpinner.getValue();
            int minCostForAgent = (int) minCostForAgentSpinner.getValue();

            ProductEntity product = new ProductEntity(
                    title,
                    productType,
                    article,
                    description,
                    image,
                    productionPersonCount,
                    productionWorkshopNumber,
                    minCostForAgent
            );
            try {
                ProductEntityManager.insert(product);
                JOptionPane.showMessageDialog(this,"Запись успешно добавлена","Добавление",JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            dispose();
            new MainForm();
        });
    }
}
