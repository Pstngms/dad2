package JumpMyBallApp.ui;

import JumpMyBallApp.entity.ProductEntity;
import JumpMyBallApp.manager.ProductEntityManager;
import JumpMyBallApp.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

public class MainForm extends BaseForm {
    private JPanel mainPanel;
    private JButton updateButton;
    private JButton viewButton;
    private JButton createButton;

    public MainForm() {
        super(800,600);
        setContentPane(mainPanel);
        initButtons();
        setVisible(true);
    }
    private void initButtons(){
        viewButton.addActionListener(e -> {
            dispose();
            new ViewProductForm();
        });
        createButton.addActionListener(e -> {
            dispose();
            new CreateProductForm();
        });
        updateButton.addActionListener(e -> {
            String s = JOptionPane.showInputDialog(this,"Введите ИД","Редактирование",JOptionPane.QUESTION_MESSAGE);
            if (s==null){
                return;
            }
            int id = -1;
            try {
                id = Integer.parseInt(s);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this,"Некорректный ИД","Ошибка",JOptionPane.ERROR_MESSAGE);
            }
            ProductEntity product = null;
            try {
                product = ProductEntityManager.selectById(id);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            if (product==null){
                JOptionPane.showMessageDialog(this,"Запись отсутствует","Ошибка",JOptionPane.ERROR_MESSAGE);
                return;
            }
            dispose();
            new UpdateProductForm(product);
        });

    }

}
