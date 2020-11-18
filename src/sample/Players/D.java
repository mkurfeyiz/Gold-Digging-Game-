package sample.Players;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class D extends Players{


    public D(int moveCost,int targetCost,int steps,int gold){

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

    //Diğer oyuncuların yapacağı hamleleri önceden sezme yeteneği bulunur. Diğer
    //oyuncuların hedeflediği altınlara onlardan önce erişemiyorsa bu altınları hariç tutar ve hedef
    //olarak diğer altın kareler içerisinden en karlı olanı seçer.
    @Override
    public void selectTarget(int moveCost,int targetCost){
        //Search algorithm

        this.gold -= moveCost;
        this.gold -= targetCost;
        this.goldSpent = moveCost + targetCost;
    }

    @Override
    public void log(Players player) {
        //logD.txt dosyasi olustur ve icine hamle bilgilerini yazdir.
        System.out.println(player.logFile.getName()+" dosyasına yazılıyor...");
        try {
            writer = new FileWriter("C:\\Users\\asus1\\Desktop\\Courses\\Yazlab1\\src\\sample\\Logs\\logs.txt",true);
            writer.write("\nD Oyuncusu\n\n");
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
