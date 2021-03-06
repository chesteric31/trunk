package be.jsams.client.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.UIResource;
import javax.swing.table.TableCellRenderer;

/**
 * Custom Table cell renderer for JSAMS with row shading and row horizontal
 * alignment for Boolean data type.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsBooleanTableCellRenderer extends JCheckBox implements TableCellRenderer, UIResource {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -6193480603193035144L;
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    /**
     * Constructor.
     */
    public JsamsBooleanTableCellRenderer() {
        super();
        setHorizontalAlignment(JLabel.CENTER);
        setBorderPainted(true);
        setOpaque(true);
    }

    /**
     * {@inheritDoc}
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        if (isSelected) {
            setBackground(table.getSelectionBackground());
        } else {
            if (row % 2 == 0) {
                setBackground(Color.WHITE);
            } else {
                setBackground(UIManager.getColor("Table.alternateRowColor"));
            }
        }

        setSelected((value != null && ((Boolean) value).booleanValue()));

        if (hasFocus) {
            setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
        } else {
            setBorder(NO_FOCUS_BORDER);
        }
        return this;
    }

}
