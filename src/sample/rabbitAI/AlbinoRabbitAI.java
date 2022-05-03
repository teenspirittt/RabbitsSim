package sample.rabbitAI;

import sample.rabbit.AlbinoRabbit;
import sample.rabbit.Rabbit;

import java.util.Random;
import java.util.Vector;

public class AlbinoRabbitAI extends BaseAI {

    public AlbinoRabbitAI(Vector<Rabbit> rabbitVector) {
        super(rabbitVector);
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
                    if (rabbit instanceof AlbinoRabbit) {
                        move((AlbinoRabbit) rabbit);
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


    public void move(AlbinoRabbit rabbit) {
        Random random = new Random();

        if (rabbit.getPosX() >= rabbit.getDestX() - 20 && rabbit.getPosX() <= rabbit.getDestX() + 20) {
            rabbit.setDestX(random.nextInt(1280 - 339));

        } else {
            double dx = (double) (rabbit.getDestX() - rabbit.getPosX()) / 10;
            rabbit.setPosX((float) (rabbit.getPosX() + dx * rabbit.rabbitSpeed));
            rabbit.moveImage();
        }
    }


}
