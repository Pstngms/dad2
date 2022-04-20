package JumpMyBallApp.ui;

import JumpMyBallApp.entity.ProductEntity;
import JumpMyBallApp.manager.ProductEntityManager;
import JumpMyBallApp.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class ViewProductForm extends BaseForm {
    private JPanel mainPanel;
    private JButton backButton;
    private JTextArea textArea;

    public ViewProductForm() {
        super(800,600);
        setContentPane(mainPanel);
        initButton();
        initTextArea();
        setVisible(true);
    }
    private void initButton(){
        backButton.addActionListener(e -> {
            dispose();
            new MainForm();
        });
    }
    private void initTextArea(){
        String s ="";

        try {
            List<ProductEntity> list =  ProductEntityManager.selectAll();
            for(ProductEntity p : list){
                s+=p;
                s+="\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        textArea.setText(s);
    }
}
