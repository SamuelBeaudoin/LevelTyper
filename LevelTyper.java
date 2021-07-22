
 
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 * @author Samuel Beaudoin
 */
public class LevelTyper extends javax.swing.JFrame implements Runnable {
	
	public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LevelTyper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LevelTyper().setVisible(true);
            }
        });
    }
   
    private boolean running = false;
   
    public LevelTyper() {
        initComponents();
        setTitle("LevelTyper");
        setLocationRelativeTo(null);
        setResizable(false);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
 
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
 
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
 
        jLabel1.setText("Text :");
 
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
 
        jLabel2.setText("Time interval :");
 
        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
 
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
 
        pack();
    }                   
 
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                        
        if(!running) {
            jTextArea1.setEnabled(false);
            jSpinner1.setEnabled(false);
            jButton1.setText("Typing in 3 seconds...");
            jButton1.setEnabled(false);
            running = true;
            new Thread(this).start();
        }
        else {
            running = false;
            jTextArea1.setEnabled(true);
            jSpinner1.setEnabled(true);
            jButton1.setText("Spam");
        }
    }                                        
   
                       
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextArea jTextArea1;
    private SecureRandom secureRandom = new SecureRandom();                
    private int randomNumber;
    
    public void run() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(LevelTyper.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] keys = jTextArea1.getText().split(" ");
        if((int) jSpinner1.getValue() < 0) {
            jSpinner1.setValue((int) 0);
        }
        int interval = (int) jSpinner1.getValue();
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LevelTyper.class.getName()).log(Level.SEVERE, null, ex);
        }
        jButton1.setEnabled(true);
        jButton1.setText("Stop");
        while(running) {
        	randomNumber=secureRandom.nextInt(keys.length);
        	int code;
            for(int i = 0; i < keys[randomNumber].length(); i++) {
            	code = KeyEvent.getExtendedKeyCodeForChar(keys[randomNumber].charAt(i));
                robot.keyPress(code);
                robot.keyRelease(code);
            }
            try {
                Thread.sleep(interval);
            } catch (InterruptedException ex) {
                Logger.getLogger(LevelTyper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
