package sample.controller;

import javafx.scene.Group;
import sample.model.Model;
import sample.rabbit.AlbinoRabbit;
import sample.rabbit.CommonRabbit;
import sample.rabbit.Rabbit;


import javax.swing.*;
import java.sql.*;


public class DataBaseHandler {
    Model model = Model.getInstance();
    private final String URL = "jdbc:postgresql://localhost:5432/RabbitSim";
    private final String username = "postgres";
    private final String password = "root";
    private final String tableName = "rabbits";
    private final String dropTableCommand = "DROP TABLE IF EXISTS rabbits";
    private final String createTableCommand = "CREATE TABLE rabbits (" +
            "id int," +
            "type varchar(6)," +
            "pos_x real," +
            "pos_y real," +
            "spawn_x real," +
            "spawn_y real," +
            "birth_time int," +
            "dest_x real," +
            "dest_y real," +
            "life_time int)";


    public void saveAll() {

        try (Connection connection = DriverManager.getConnection(URL, username, password)) {

            Class.forName("org.postgresql.Driver");
            Statement statement = connection.createStatement();
            statement.executeUpdate(dropTableCommand);
            statement.executeUpdate(createTableCommand);
            synchronized (model.getRabbitsVector()) {
                for (Rabbit rabbit : model.getRabbitsVector()) {
                    if (rabbit instanceof CommonRabbit)
                        statement.executeUpdate(writeCrRabbit((CommonRabbit) rabbit));
                    else
                        statement.executeUpdate(writeAlRabbit((AlbinoRabbit) rabbit));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String writeCrRabbit(CommonRabbit commonRabbit) {
        String command = "INSERT INTO " + tableName + " VALUES (";
        command += commonRabbit.getID() + ",";
        command += "'Common', ";
        command += commonRabbit.getPosX() + ",";
        command += commonRabbit.getPosY() + ",";
        command += commonRabbit.getBirthX() + ",";
        command += commonRabbit.getBirthY() + ",";
        command += commonRabbit.getBirthTime() + ",";
        command += commonRabbit.getDestX() + ",";
        command += commonRabbit.getDestY() + ",";
        command += commonRabbit.getLifeTime() - (model.gettTick() - commonRabbit.getBirthTime()) + ");";
        System.out.println(command);
        return command;
    }

    private String writeAlRabbit(AlbinoRabbit albinoRabbit) {
        String command = "INSERT INTO " + tableName + " VALUES (";
        command += albinoRabbit.getID() + ",";
        command += "'Albino', ";
        command += albinoRabbit.getPosX() + ",";
        command += albinoRabbit.getPosY() + ",";
        command += albinoRabbit.getBirthX() + ",";
        command += albinoRabbit.getBirthY() + ",";
        command += albinoRabbit.getBirthTime() + ",";
        command += albinoRabbit.getDestX() + ",";
        command += albinoRabbit.getDestY() + ",";
        command += albinoRabbit.getLifeTime() - (model.gettTick() - albinoRabbit.getBirthTime()) + ");";
        return command;
    }

    public void saveCrRabbits() {
        try (Connection connection = DriverManager.getConnection(URL, username, password)) {
            Class.forName("org.postgresql.Driver");
            Statement statement = connection.createStatement();
            statement.executeUpdate(dropTableCommand);
            statement.executeUpdate(createTableCommand);
            synchronized (model.getRabbitsVector()) {
                for (Rabbit rabbit : model.getRabbitsVector()) {
                    if (rabbit instanceof CommonRabbit)
                        statement.executeUpdate(writeCrRabbit((CommonRabbit) rabbit));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveAlRabbits() {
        try (Connection connection = DriverManager.getConnection(URL, username, password)) {
            Class.forName("org.postgresql.Driver");
            Statement statement = connection.createStatement();
            statement.executeUpdate(dropTableCommand);
            statement.executeUpdate(createTableCommand);
            synchronized (model.getRabbitsVector()) {
                for (Rabbit rabbit : model.getRabbitsVector()) {
                    if (rabbit instanceof AlbinoRabbit)
                        statement.executeUpdate(writeAlRabbit((AlbinoRabbit) rabbit));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadAll(Group root) {
        try (Connection connection = DriverManager.getConnection(URL, username, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM rabbits");
            synchronized (model.getRabbitsVector()) {
                while (resultSet.next()) {
                    String type = resultSet.getString(2);
                    Rabbit rabbit = type.equals("Common") ? readCrRabbit(resultSet, root) : readAlRabbit(resultSet, root);
                    model.getRabbitsVector().add(rabbit);
                    model.getRabbitsIdSet().add(rabbit.getID());
                    model.getRabbitsLifeTimeMap().put(rabbit.getID(), model.gettTick());
                    if (type.equals("Common")) {
                        model.setCrCount(model.getCrCount() + 1);
                    } else {
                        model.setAlCount(model.getAlCount() + 1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Rabbit readCrRabbit(ResultSet resultSet, Group root) throws SQLException {
        int id = resultSet.getInt(1);
        float posX = resultSet.getFloat(3);
        float posY = resultSet.getFloat(4);
        float spawn_x = resultSet.getFloat(5);
        float spawn_y = resultSet.getFloat(6);
        int birthTime = resultSet.getInt(7);
        float dest_x = resultSet.getInt(8);
        float dest_y = resultSet.getInt(9);
        int lifetime = resultSet.getInt(10);

        CommonRabbit commonRabbit = new CommonRabbit();
        commonRabbit.spawn(posX, posY, root);
        commonRabbit.setBirthTime(birthTime);
        commonRabbit.setLifeTime(lifetime);
        commonRabbit.setID(id);
        commonRabbit.setBirthX(spawn_x);
        commonRabbit.setBirthY(spawn_y);
        commonRabbit.setDestX(dest_x);
        commonRabbit.setDestY(dest_y);

        return commonRabbit;
    }

    private Rabbit readAlRabbit(ResultSet resultSet, Group root) throws SQLException {
        int id = resultSet.getInt(1);
        float posX = resultSet.getFloat(3);
        float posY = resultSet.getFloat(4);
        float spawn_x = resultSet.getFloat(5);
        float spawn_y = resultSet.getFloat(6);
        int birthTime = resultSet.getInt(7);
        float dest_x = resultSet.getInt(8);
        float dest_y = resultSet.getInt(9);
        int lifetime = resultSet.getInt(10);

        AlbinoRabbit albinoRabbit = new AlbinoRabbit();
        albinoRabbit.spawn(posX, posY, root);
        albinoRabbit.setBirthTime(birthTime);
        albinoRabbit.setLifeTime(lifetime);
        albinoRabbit.setID(id);
        albinoRabbit.setBirthX(spawn_x);
        albinoRabbit.setBirthY(spawn_y);
        albinoRabbit.setDestX(dest_x);
        albinoRabbit.setDestY(dest_y);
        return albinoRabbit;
    }


}

