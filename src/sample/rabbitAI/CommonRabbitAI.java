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

        int checkX = 0;
        int checkY = 0;


        if (rabbit.getDestX() == rabbit.getPosX() && rabbit.getDestY() < rabbit.getPosY()) {
            checkX = rabbit.getPosX();
            checkY = (int) (rabbit.getPosY() - rabbit.getDestY() * rabbit.rabbitSpeed);
        }

        if (rabbit.getDestX() < rabbit.getPosX() && rabbit.getDestY() < rabbit.getPosY()){
            checkX = (int) (rabbit.getPosX() - rabbit.getDestX() * rabbit.rabbitSpeed);
            checkY = (int) (rabbit.getPosY() - rabbit.getDestY() * rabbit.rabbitSpeed);
        }

        if (rabbit.getDestX() < rabbit.getPosX() && rabbit.getDestY() == rabbit.getPosY()){
            checkX = (int) (rabbit.getPosX() - rabbit.getDestX() * rabbit.rabbitSpeed);
            checkY = rabbit.getPosY();
        }

        if (rabbit.getDestX() < rabbit.getPosX() && rabbit.getDestY() > rabbit.getPosY()){
            checkX = (int) (rabbit.getPosX() - rabbit.getDestX() * rabbit.rabbitSpeed);
            checkY = (int) (rabbit.getPosY() + rabbit.getDestY() * rabbit.rabbitSpeed);
        }

        if (rabbit.getDestX() == rabbit.getPosX() && rabbit.getDestY() > rabbit.getPosY()){
            checkX = rabbit.getPosX();
            checkY = (int) (rabbit.getPosY() + rabbit.getDestY() * rabbit.rabbitSpeed);
        }

        if (rabbit.getDestX() > rabbit.getPosX() && rabbit.getDestY() > rabbit.getPosY()){
            checkX = (int) (rabbit.getPosX() + rabbit.getDestX() * rabbit.rabbitSpeed);
            checkY = (int) (rabbit.getPosY() + rabbit.getDestY() * rabbit.rabbitSpeed);
        }

        if (rabbit.getDestX() > rabbit.getPosX() && rabbit.getDestY() == rabbit.getPosY()){
            checkX = (int) (rabbit.getPosX() + rabbit.getDestX() * rabbit.rabbitSpeed);
            checkY = rabbit.getPosY();
        }

        if (rabbit.getDestX() > rabbit.getPosX() && rabbit.getDestY() < rabbit.getPosY()){
            checkX = (int) (rabbit.getPosX() + rabbit.getDestX() * rabbit.rabbitSpeed);
            checkY = (int) (rabbit.getPosY() - rabbit.getDestY() * rabbit.rabbitSpeed);
        }

        if (checkX > 98 && checkX < 941) {
            rabbit.setPosX(checkX);

        } else {
            rabbit.setDestY(20 + random.nextInt(720 - 77 - 20));
            rabbit.setDestX(random.nextInt(1024 - 339));
        }

        if (checkY > 75 && checkY < 650) {
            rabbit.setPosY(checkY);
        } else {
            rabbit.setDestY(20 + random.nextInt(720 - 77 - 20));
            rabbit.setDestX(random.nextInt(1024 - 339));
        }

        rabbit.moveImage();
    }

}




/* Random random = new Random();

 */


/*public void addVector(myVector newWay, float speed,CommonRabbit rabbit) {
        myVector rabbitVec = new myVector();
        myVector newVec = new myVector();

        rabbitVec.x = rabbit.way.x * rabbit.rabbitSpeed;
        rabbitVec.y = rabbit.way.y * rabbit.rabbitSpeed;

        newVec.x = rabbit.way.x * speed;
        newVec.y = rabbit.way.y * speed;

        rabbit.way.x = rabbitVec.x + newVec.x;
        rabbit.way.y = rabbitVec.y + newVec.y;

        normalize(rabbit);
    }

    private void normalize(CommonRabbit rabbit) {
        rabbit.rabbitSpeed = (int)Math.sqrt(rabbit.way.x * rabbit.way.x + rabbit.way.y * rabbit.way.y);
        rabbit.way.x *= 1.0 /rabbit.rabbitSpeed;
        rabbit.way.x *= 1.0 /rabbit.rabbitSpeed;
    }*/