package sample.Players;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class C extends Players{


    public C(int moveCost,int targetCost,int steps,int gold){

        this.gold = gold;
        this.steps = steps;
        this.moveCost = moveCost;
        this.targetCost = targetCost;

        try {
            this.logFile = new File("C:\\Users\\asus1\\Desktop\\Courses\\Yazlab1\\src\\sample\\Logs\\logs.txt");
            if (logFile.createNewFile()) {
                System.out.println(logFile.getName() + " dosyası oluşturuldu.");
            }

        } catch (IOException e) {
            System.out.println("Bir hata meydana geldi.");
            e.printStackTrace();
        }

    }

    //Hedef belirlemeden once kendine en yakin belirli sayidaki gizli altini acar.
    // Bu açılan altınlarla birlikte tüm altınlar içerisinden B oyuncusu
    //gibi en karlı olanı hedefler
    @Override
    public void selectTarget(int moveCost,int targetCost){
        //Search algorithm

        this.gold -= moveCost;
        this.gold -= targetCost;
        this.goldSpent = moveCost + targetCost;
    }

    @Override
    public void log(Players player) {
        //logC.txt dosyasi olustur ve icine hamle bilgilerini yazdir.
        System.out.println(player.logFile.getName()+" dosyasına yazılıyor...");
        try {
            writer = new FileWriter("C:\\Users\\asus1\\Desktop\\Courses\\Yazlab1\\src\\sample\\Logs\\logs.txt",true);
            writer.write("\nC Oyuncusu\n\n");
            writer.write("Kalan Altın Miktarı : "+player.gold+" \nHarcanan Toplam Altın Miktarı :"+player.goldSpent
                    +" \nToplam Atılan Adım Sayısı : "+player.stepsCount);
            writer.write("\n----\n");
            writer.close();
            System.out.println("Yazma işlemi tamamlandı!");
        }
        catch (Exception e) {
            System.out.println("Bir sorun meydana geldi.");
            e.printStackTrace();
        }

    }

}
