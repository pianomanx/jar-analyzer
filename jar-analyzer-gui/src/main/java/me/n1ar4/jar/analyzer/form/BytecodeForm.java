package me.n1ar4.jar.analyzer.form;

import me.n1ar4.jar.analyzer.asm.ASMPrint;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import jsyntaxpane.syntaxkits.JavaSyntaxKit;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BytecodeForm {
    public JPanel parentPanel;
    private JScrollPane scroll;
    private JEditorPane editPanel;

    public BytecodeForm(String className, boolean flag) {
        editPanel.setEditorKit(new JavaSyntaxKit());

        className = className.replace("/", File.separator);
        className = className.replace(".", File.separator);
        String targetPath = String.format("temp%s%s.class", File.separator, className);
        if (!Files.exists(Paths.get(targetPath))) {
            targetPath = String.format("temp%sBOOT-INF%sclasses%s%s.class",
                    File.separator, File.separator, File.separator, className);
            if (!Files.exists(Paths.get(targetPath))) {
                targetPath = String.format("temp%sWEB-INF%sclasses%s%s.class",
                        File.separator, File.separator, File.separator, className);
                if (!Files.exists(Paths.get(targetPath))) {
                    return;
                }
            }
        }
        try {
            InputStream is = Files.newInputStream(Paths.get(targetPath));
            String data = ASMPrint.getPrint(is, flag);
            editPanel.setText(data);
            editPanel.setCaretPosition(0);
        } catch (Exception ignored) {
        }
    }

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
        parentPanel = new JPanel();
        parentPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        parentPanel.setBorder(BorderFactory.createTitledBorder(null, "Show Bytecode", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        scroll = new JScrollPane();
        parentPanel.add(scroll, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(800, 800), null, 0, false));
        editPanel = new JEditorPane();
        scroll.setViewportView(editPanel);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return parentPanel;
    }
}