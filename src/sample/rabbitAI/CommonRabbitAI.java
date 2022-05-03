package sample.rabbitAI;

import sample.rabbit.*;


import java.util.Random;
import java.util.Vector;

public class CommonRabbitAI extends BaseAI {

    public CommonRabbitAI(Vector<Rabbit> rabbitsVector) {
        super(rabbitsVector);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (pauseLock) {
                if (paused) {
                    try {
                        pauseLock.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }

            synchronized (rabbitsVector) {
                for (Rabbit rabbit : rabbitsVector) {
                    if (rabbit instanceof CommonRabbit) {
                        move((CommonRabbit) rabbit);
                    }
                }
            }

            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void move(CommonRabbit rabbit) {
        Random random = new Random();

        if ((rabbit.getPosX() >= rabbit.getDestX() - 20 && rabbit.getPosX() <= rabbit.getDestX() + 20) &&
                (rabbit.getPosY() >= rabbit.getDestY() - 10 && rabbit.getPosY() <= rabbit.getDestY() + 10)) {
            rabbit.setDestX(random.nextInt(1280 - 339));
            rabbit.setDestY(20 + random.nextInt(720 - 77 - 20));
        } else {
            double dx = (double) (rabbit.getDestX() - rabbit.getPosX()) / 10;
            double dy = (double) (rabbit.getDestY() - rabbit.getPosY()) / 10;
            rabbit.setPosX((float) (rabbit.getPosX() + dx * rabbit.rabbitSpeed));
            rabbit.setPosY((float) (rabbit.getPosY() + dy * rabbit.rabbitSpeed));
            rabbit.moveImage();
        }
    }

}
