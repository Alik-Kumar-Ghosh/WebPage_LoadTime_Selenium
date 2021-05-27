import org.openqa.selenium.By;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.Scanner;

public class PageLoadTime {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter site : ");
        String web= sc.next();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String time1 = LocalTime.now().toString();
        driver.get(web);
        int min1=Integer.parseInt(time1.substring(3,5));
        int sec1=Integer.parseInt(time1.substring(6,8));
        int msec1=Integer.parseInt(time1.substring(9,11));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        String time2 = LocalTime.now().toString();
        int min2=Integer.parseInt(time2.substring(3,5));
        int sec2=Integer.parseInt(time2.substring(6,8));
        int msec2=Integer.parseInt(time2.substring(9,11));
        int calc_min=min2-min1;
        int calc_sec=sec2-sec1;
        int calc_msec=msec2-msec1;
        if (calc_sec<0)
            calc_sec=-calc_sec;
        if (calc_msec<0)
            calc_msec=-calc_msec;
        if(calc_min>0)
            System.out.println("Time taken by site to load is " + calc_min + " min " + calc_sec + " seconds " + calc_msec + " milliseconds ");
        else
            System.out.println("Time taken by site to load is "+calc_sec+" seconds "+calc_msec+" milliseconds ");
        save_record(web,calc_min,calc_sec,calc_msec);
        driver.close();
    }
    public static void save_record(String web,int calc_min,int calc_sec,int calc_msec){
        try {
            FileWriter fw = new FileWriter("data.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(web+" -> "+calc_min+" minutes "+calc_sec+" second "+calc_msec+" milliseconds ");
            pw.flush();
            pw.close();
            System.out.println("Data Saved successfully ");
        }
        catch (Exception e){
            System.out.println("Data not saved ");
        }
    }
}
