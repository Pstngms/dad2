package JumpMyBallApp.ui;

import JumpMyBallApp.entity.ProductEntity;
import JumpMyBallApp.manager.ProductEntityManager;
import JumpMyBallApp.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

public class UpdateProductForm extends BaseForm {
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
    private JButton deleteButton;
    private JTextField idTextField;
    private ProductEntity product;

    public UpdateProductForm(ProductEntity product) {
        super(800,600);
        setContentPane(mainPanel);
        this.product = product;
        initButtons();
        initFields();
        setVisible(true);
    }
    private void initFields(){
        idTextField.setText(String.valueOf(product.getId()));
        titleTextField.setText(product.getTitle());
        productTypeTextField.setText(product.getProductType());
        articleSpinner.setValue(product.getArticleNumber());
        descriptionTextField.setText(product.getDescription());
        imageTextField.setText(product.getImage());
        productionPersonCountSpinner.setValue(product.getProductionPersonCount());
        productionWorkshopNumberSpinner.setValue(product.getProductionWorkshopNumber());
        minCostForAgentSpinner.setValue(product.getMinCostForAgent());
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

            product.setTitle(title);
            product.setProductType(productType);
            product.setArticleNumber(article);
            product.setDescription(description);
            product.setImage(image);
            product.setProductionPersonCount(productionPersonCount);
            product.setProductionWorkshopNumber(productionWorkshopNumber);
            product.setMinCostForAgent(minCostForAgent);
            try {
                ProductEntityManager.update(product);
                JOptionPane.showMessageDialog(this,"Запись успешно изменена","Редактироание",JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            dispose();
            new MainForm();
        });
        deleteButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this,"Вы точно хотите удалить?","Удаление",JOptionPane.YES_NO_OPTION)
            ==JOptionPane.YES_OPTION){
                try {
                    ProductEntityManager.delete(product.getId());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(this,"Запись успешно удалена","Удаление",JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new MainForm();
            }
        });
    }
}
