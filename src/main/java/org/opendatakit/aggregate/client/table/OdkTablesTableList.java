package org.opendatakit.aggregate.client.table;

import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;

import org.opendatakit.aggregate.client.odktables.TableEntryClient;
import org.opendatakit.aggregate.client.odktables.TableResourceClient;
import org.opendatakit.aggregate.client.widgets.OdkTablesDeleteTableButton;
import org.opendatakit.aggregate.client.widgets.OdkTablesShowTableButton;
import org.opendatakit.aggregate.client.widgets.OdkTablesShowTableFilesButton;
import org.opendatakit.aggregate.client.widgets.TablesAdminDeleteButton;
import org.opendatakit.aggregate.odktables.entity.TableEntry;


/**
 * This is the class that displays the list of the database tables that
 * belong to ODK Tables to the user. It may eventually be the point of interaction
 * to enter the tables and add coarse control. 
 * <br>
 * Based on OdkAdminListTable.
 * @author sudar.sam@gmail.com
 *
 */
public class OdkTablesTableList extends FlexTable {

	// This will be the column with the table name
	private static final int TABLE_NAME_COLUMN = 1;
	private static final String TABLE_NAME_HEADING = "Table Name";
	
	private static final int DELETE_BUTTON_COLUMN = 0;
	private static final String DELETE_BUTTON_HEADING = "Delete";
	
	private static final int VIEW_TABLE_BUTTON_COLUMN = 2;
	private static final String VIEW_TABLE_BUTTON_HEADING = "";
	
	private static final int TABLE_FILES_BUTTON_COLUMN = 3;
	private static final String TABLE_FILE_BUTTON_HEADING = "";
	
	
	public OdkTablesTableList() {
		// add styling
		//addStyleName("tableList");
		getElement().setId("table_list");
	}
	
	/**
	 * Updates the list of tables.
	 * @param tableList the list of tables to be displayed.
	 */
	public void updateTableList(List<TableEntryClient> tables) {
		if (tables == null) {
			return;
		}
		
		removeAllRows();
		
		removeAllRows();
		// now create the table headers
		setText(0, TABLE_NAME_COLUMN, TABLE_NAME_HEADING);
		addStyleName("dataTable");
		getRowFormatter().addStyleName(0, "titleBar");
		
		if (tables.size() == 0) {
			setWidget(1, TABLE_NAME_COLUMN, new HTML("<i> There are no tables to display. </i>"));
		} else {
			
			setText(0, DELETE_BUTTON_COLUMN, DELETE_BUTTON_HEADING);
			setText(0, VIEW_TABLE_BUTTON_COLUMN, VIEW_TABLE_BUTTON_HEADING);
			setText(0, TABLE_FILES_BUTTON_COLUMN, TABLE_FILE_BUTTON_HEADING);
			setText(0, TABLE_NAME_COLUMN, TABLE_NAME_HEADING);
			
			for (int i = 0; i < tables.size(); i++) {
				TableEntryClient table = tables.get(i);
				// this will maintain the row you're adding to, always +1 
				// because of the title row
				int j = i + 1;
				setWidget(j, TABLE_NAME_COLUMN, new HTML("<b>" + table.getTableKey()+ "</b>"));
				setWidget(j, DELETE_BUTTON_COLUMN, new OdkTablesDeleteTableButton(
						this, table.getTableId()));
				setWidget(j, VIEW_TABLE_BUTTON_COLUMN, 
				    new OdkTablesShowTableButton(this, table.getTableId(),
				    table.getTableKey()));
				setWidget(j, TABLE_FILES_BUTTON_COLUMN,
				    new OdkTablesShowTableFilesButton(this, table.getTableId()));
				
				if (j % 2 == 0) {
					getRowFormatter().addStyleName(j, "evenTableRow");
				}
			}
		}
	}
}