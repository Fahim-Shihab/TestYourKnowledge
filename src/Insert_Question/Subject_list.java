package Insert_window;

import database.Show_subject_table;

import java.sql.SQLException;
import java.util.Iterator;

public class Subject_list implements Iterator {


    Show_subject_table show_subject_table = null;
    private static int index = 0;
    private int array_size;


    public Subject_list() {


    }

    public int getArray_size() throws SQLException {

        show_subject_table = new Show_subject_table();
        this.array_size = show_subject_table.table_size;

        return this.array_size;

    }

    @Override
    public boolean hasNext() {
        try {
            show_subject_table = new Show_subject_table();
            if( index >= show_subject_table.table_size )return false;
                else return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String next() {
        try {
            show_subject_table = new Show_subject_table();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return String.valueOf(show_subject_table.subject_array[index++]);
    }
}
