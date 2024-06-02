package medicalhistory.databasegraphicInterface;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;

public class Calendar extends JFrame {
	JCalendar calendar;
    public Calendar() {
        setTitle("Calendario");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        // Crear el calendario
        calendar = new JCalendar();
        panel.add(calendar);
    }
	public JCalendar getCalendar() {
		return calendar;
	}
	public void setCalendar(JCalendar calendar) {
		this.calendar = calendar;
	}

    
}
