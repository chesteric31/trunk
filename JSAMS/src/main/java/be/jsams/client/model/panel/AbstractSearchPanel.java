package be.jsams.client.model.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.renderer.JsamsBooleanTableCellRenderer;
import be.jsams.client.renderer.JsamsTableCellRenderer;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsButtonsInterface;
import be.jsams.client.swing.component.JsamsButtonsPanel;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.component.JsamsStatusBar;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.server.service.Service;

import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.validation.Severity;
import com.jgoodies.validation.ValidationMessage;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.DefaultValidationResultModel;
import com.jgoodies.validation.view.ValidationResultViewFactory;

/**
 * Search generic panel.
 * 
 * @param <B> an extension of {@link AbstractIdentityBean}
 * @param <L> an extension of {@link MouseListener}
 * @param <S> an extension of {@link Service}
 * @param <V> an extension of {@link Validator}
 * 
 * @author chesteric31
 * @version $$Rev: 689 $$ $$Date::                  $$ $$Author$$
 */
public abstract class AbstractSearchPanel<B extends AbstractIdentityBean<?, ?>, L extends MouseListener,
        S extends Service<B>, V extends Validator<B>> extends JPanel implements JsamsButtonsInterface {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4277242728022039298L;

    private static final int DEFAULT_V_GAP = 10;

    private ValidationResultModel validationResultModel = new DefaultValidationResultModel();

    private JsamsStatusBar statusBar = new JsamsStatusBar();

    private Validator<B> validator;

    private B model;

    private L mouseListener;

    private S service;

    private JsamsButtonsPanel buttonsPanel;

    private JsamsTable resultTable = null;

    private JsamsButton buttonAdd = null;
    private JsamsButton buttonRemove = null;
    private JsamsButton buttonModify = null;

    private boolean showButtons;

    public static final int THREE = 3;

    /**
     * Constructor.
     * 
     * @param bean the {@link AbstractIdentityBean}
     * @param listener the {@link MouseListener}
     * @param service the {@link Service}
     * @param validator the {@link Validator}
     * @param showButtons a boolean that indicates to show or not a management
     *            buttons (add, modify and remove)
     */
    public AbstractSearchPanel(B bean, L listener, S service, V validator, boolean showButtons) {
        super();
        this.model = bean;
        this.mouseListener = listener;
        this.service = service;
        this.validator = validator;
        this.showButtons = showButtons;
        setLayout(new BorderLayout());
        buttonsPanel = new JsamsButtonsPanel(this, true, true, true);
        buildMainPanel();
        setDefaultKeyActions();
        setTableRenderer();
    }

    /**
     * 
     * @return the model
     */
    public B getModel() {
        return model;
    }

    /**
     * 
     * @param model the model to set
     */
    public void setModel(B model) {
        this.model = model;
    }

    /**
     * 
     * @return the {@link MouseListener}
     */
    public L getMouseListener() {
        return mouseListener;
    }

    /**
     * 
     * @param mouseListener the {@link MouseListener} to set
     */
    public void setMouseListener(L mouseListener) {
        this.mouseListener = mouseListener;
    }

    /**
     * 
     * @return the service
     */
    public S getService() {
        return service;
    }

    /**
     * 
     * @param service the service to set
     */
    public void setService(S service) {
        this.service = service;
    }

    /**
     * 
     * @return the {@link Validator}
     */
    public Validator<B> getValidator() {
        return validator;
    }

    /**
     * 
     * @param validator the {@link Validator} to set
     */
    public void setValidator(Validator<B> validator) {
        this.validator = validator;
    }

    /**
     * 
     * @return the result {@link JsamsTable}
     */
    public JsamsTable getResultTable() {
        return resultTable;
    }

    /**
     * @return the statusBar
     */
    public JsamsStatusBar getStatusBar() {
        return statusBar;
    }

    /**
     * @param statusBar the statusBar to set
     */
    public void setStatusBar(JsamsStatusBar statusBar) {
        this.statusBar = statusBar;
    }

    /**
     * 
     * @return true, if we have to display the management buttons panel, false
     *         otherwise
     */
    public boolean isShowButtons() {
        return showButtons;
    }

    /**
     * 
     * @param showButtons the boolean to set, if we have to display the
     *            management buttons panel or not
     */
    public void setShowButtons(boolean showButtons) {
        this.showButtons = showButtons;
    }

    /**
     * 
     * @param resultTable the result {@link JsamsTable} to set
     */
    public void setResultTable(JsamsTable resultTable) {
        this.resultTable = resultTable;
    }

    /**
     * {@inheritDoc}
     */
    public abstract void performCancel();

    /**
     * {@inheritDoc}
     */
    public abstract void performOk();

    /**
     * Refreshes the result table to press to OK.
     */
    public void refresh() {
        performOk();
    }

    /**
     * {@inheritDoc}
     */
    public void performReset() {
        model.clear();
        getResultTable().clear();
        getStatusBar().clear();
        updateUI();
    }

    /**
     * Builds the adding button.
     * 
     * @return the adding {@link JsamsButton}
     */
    private JsamsButton buildButtonAdd() {
        JsamsButton buttonAdd = new JsamsButton(IconUtil.MENU_ICON_PREFIX + "actions/list-add.png");
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performButtonAdd();
            }
        });
        return buttonAdd;
    }

    /**
     * The action to perform when click onto adding button.
     */
    protected abstract void performButtonAdd();

    /**
     * The action to perform when click onto modifying button.
     */
    protected abstract void performButtonModify();

    /**
     * The action to perform when click onto removing button.
     */
    protected abstract void performButtonRemove();

    /**
     * Builds the removing button.
     * 
     * @return the removing {@link JsamsButton}
     */
    private JsamsButton buildButtonRemove() {
        JsamsButton buttonRemove = new JsamsButton(IconUtil.MENU_ICON_PREFIX + "actions/list-remove.png");
        buttonRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performButtonRemove();
            }
        });
        return buttonRemove;
    }

    /**
     * Builds the modifying button.
     * 
     * @return the modifying {@link JsamsButton}
     */
    private JsamsButton buildButtonModify() {
        JsamsButton buttonModify = new JsamsButton(IconUtil.MENU_ICON_PREFIX + "apps/accessories-text-editor.png");
        buttonModify.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performButtonModify();
            }
        });
        return buttonModify;
    }

    /**
     * Builds the main panel contained all the components.
     */
    protected void buildMainPanel() {
        JPanel searchCriteriaPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(2, 1);
        gridLayout.setVgap(DEFAULT_V_GAP);
        searchCriteriaPanel.setLayout(gridLayout);
        // adding search criteria panel
        searchCriteriaPanel.add(this.model.getView().createSearchView());

        JPanel northPanel = new JPanel();
        BorderLayout buttonsLayout = new BorderLayout();
        buttonsLayout.setVgap(DEFAULT_V_GAP);
        northPanel.setLayout(buttonsLayout);
        northPanel.add(new JSeparator(), BorderLayout.NORTH);
        northPanel.add(buttonsPanel);
        searchCriteriaPanel.add(buttonsPanel);

        this.add(searchCriteriaPanel, BorderLayout.NORTH);

        resultTable = new JsamsTable(true);
        resultTable.addMouseListener(mouseListener);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(new TitledBorder(JsamsI18nResource.SEARCH_RESULTS.getTranslation()));
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
        southPanel.setBorder(BorderFactory.createEtchedBorder());

        if (showButtons) {
            buttonAdd = buildButtonAdd();
            buttonRemove = buildButtonRemove();
            buttonModify = buildButtonModify();
            JsamsButton[] buttons = new JsamsButton[THREE];
            buttons[0] = buttonAdd;
            buttons[1] = buttonRemove;
            buttons[2] = buttonModify;
            southPanel.add(ButtonBarFactory.buildCenteredBar(buttons));
        }
        southPanel.add(statusBar);

        this.add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Sets the default keys actions.
     */
    private void setDefaultKeyActions() {
        // Automatically choose OK when Enter Key is pressed
        int noModifiers = 0;
        KeyStroke okKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, noModifiers, false);
        InputMap inputMap = this.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(okKey, JsamsButtonsPanel.OK_ACTION_KEY);
        this.getActionMap().put(JsamsButtonsPanel.OK_ACTION_KEY, buttonsPanel.getButtonOk().getAction());

        KeyStroke resetKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, noModifiers, false);
        inputMap.put(resetKey, JsamsButtonsPanel.RESET_ACTION_KEY);
        this.getActionMap().put(JsamsButtonsPanel.RESET_ACTION_KEY, buttonsPanel.getButtonReset().getAction());
    }

    /**
     * Method called by the children class for validation.
     * 
     * @param criteria the criteria object to validate
     * @return true if no error occurred, false otherwise
     */
    protected boolean prePerformOk(B criteria) {
        ValidationResult result = validator.validate(criteria);
        validationResultModel.setResult(result);
        boolean success = true;
        if (result.hasMessages()) {
            statusBar.removeAll();
            statusBar.repaint();
            List<ValidationMessage> messages = validationResultModel.getResult().getMessages();
            for (ValidationMessage message : messages) {
                JsamsLabel label = new JsamsLabel(message.formattedText().replace(".", ""));
                if (message.severity() == Severity.ERROR) {
                    label.setIcon(ValidationResultViewFactory.getErrorIcon());
                } else if (message.severity() == Severity.WARNING) {
                    label.setIcon(ValidationResultViewFactory.getWarningIcon());
                }
                statusBar.addComponent(label);
            }
            statusBar.validate();
            success = false;
        }
        return success;
    }

    /**
     * Method called by the children class for post searching.
     */
    protected void postPerformOk() {
        int size = getResultTable().getModel().getRowCount();
        statusBar.removeAll();
        statusBar.repaint();
        if (size > 0) {
            JsamsLabel label = null;
            if (size == 1) {
                label = new JsamsLabel(JsamsI18nResource.TABLE_ONE_RESULT);
                label.setIcon(ValidationResultViewFactory.getInfoIcon());
            } else if (size > 1) {
                Object[] parameters = new Object[1];
                parameters[0] = size;
                I18nString text = new I18nString("table.info.results", parameters);
                label = new JsamsLabel(text);
            }
            label.setIcon(ValidationResultViewFactory.getInfoIcon());
            statusBar.addComponent(label);
            statusBar.validate();
        } else {
            statusBar.clear();
        }
    }

    /**
     * Sets the default JSAMS renderer for result table.
     */
    private void setTableRenderer() {
        JTableHeader tableHeader = resultTable.getTableHeader();
        TableCellRenderer headerRenderer = tableHeader.getDefaultRenderer();

        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        resultTable.setAutoCreateRowSorter(true);
        JsamsTableCellRenderer defaultCellRenderer = new JsamsTableCellRenderer();
        resultTable.setDefaultRenderer(Long.class, defaultCellRenderer);
        resultTable.setDefaultRenderer(Integer.class, defaultCellRenderer);
        resultTable.setDefaultRenderer(Double.class, defaultCellRenderer);
        resultTable.setDefaultRenderer(String.class, defaultCellRenderer);
        resultTable.setDefaultRenderer(Boolean.class, new JsamsBooleanTableCellRenderer());
        resultTable.setDefaultRenderer(Date.class, defaultCellRenderer);
    }

}
