package sample.Players;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Path
//C:\Users\asus1\Desktop\Courses\Yazlab1\src\sample\Logs
public class A extends Players{

    public A(int moveCost,int targetCost,int steps,int gold){

        this.gold = gold;
        this.steps = steps;
        this.moveCost = moveCost;
        this.targetCost = targetCost;
        try {
            this.logFile = new File("C:\\Users\\asus1\\Desktop\\Courses\\Yazlab1\\src\\sample\\Logs\\logA.txt");
            if (logFile.createNewFile()) {
                System.out.println(logFile.getName() + " dosyası oluşturuldu.");
            }

        } catch (IOException e) {
            System.out.println("Bir hata meydana geldi.");
            e.printStackTrace();
        }

    }

    //En yakindaki altini hedefler
    @Override
    public void selectTarget(int moveCost,int targetCost){
        //Search algorithm


        this.gold -= moveCost;
        this.gold -= targetCost;
        this.goldSpent = moveCost + targetCost;
    }

    @Override
    public void log(Players player) {
        System.out.println(player.logFile.getName()+" dosyasına yazılıyor...");
        //logA.txt dosyasi olustur ve icine hamle bilgilerini yazdir.
        try {
            writer = new FileWriter("C:\\Users\\asus1\\Desktop\\Courses\\Yazlab1\\src\\sample\\Logs\\logA.txt");
            writer.write("----\n");
            writer.write("Kalan Altın Miktarı : "+player.gold+" \nHarcanan Toplam Altın Miktarı :"+player.goldSpent
                    +" \nToplam Atılan Adım Sayısı : "+player.stepsCount);
            writer.write("\n----\n");
            writer.close();
        }
        catch (Exception e) {
            System.out.println("Bir sorun meydana geldi.");
            e.printStackTrace();
        }


        System.out.println("Yazma işlemi tamamlandı!");

    }

}
