
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String date;
        System.out.println("Mời ban nhap ngay: ");
        date = sc.nextLine();
        Calendar c = Calendar.getInstance();
        int thang = c.get(Calendar.MONTH) + 1;
        int nam = c.get(Calendar.YEAR);
        int ngay = c.get(Calendar.DAY_OF_MONTH);

        LocalDate localDate = LocalDate.now();

        Date ngay1 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            ngay1 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(ngay1.getMonth() + 1);
        System.out.println(ngay1.getYear() + 1900);
        System.out.println(ngay1.getDate());
        
        System.out.println("---------");
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfMonth());

        System.out.println(nam + "-" + thang + "-" + ngay);
    }
}
