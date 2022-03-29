package sample;

import javafx.application.Application;
import javafx.stage.Stage;


//todo config from habitat

// todo list for 2 lab

// TODO (1)
//  добавить группу из 2 исключающих переключателей:
//  «Показывать время симуляции» и «Скрывать время симуляции».
//  Клавиша T должна функционировать по-прежнему;
// ! [-]

// TODO (2)
//  добавить в программу главное в меню и панель
//  инструментов, в которых продублировать основные команды
//  вашего интерфейса пользователя;
// ! [-]

// TODO (3)
//  при остановке симуляции должно появляться модальное
//  диалоговое окно (при условии, что оно разрешено) с информацией о
//  количестве и типе сгенерированных объектов, а также времени симуляции.
//  Вся информация выводится в элементе TextArea, недоступном для редактирования.
//  В диалоговом окне должно быть 2 кнопки: «ОК» и «Отмена». При нажатии
//  на «ОК» симуляции останавливается, а при нажатии на «Отмена»,
//  соответственно продолжается;
// ! [-]

// TODO (3.1)
//  добавить переключатель «Показывать информацию»,
//  который разрешает отображение модального диалога из (3);
// ! [-]

// TODO (4)
//  предусмотреть проверку данных вводимых пользователем.
//  При вводе неверного значения обрабатывать исключительную ситуацию:
//  выставлять значение по умолчанию и выводить диалоговое окно с сообщением об ошибке;
// ! [-]

// TODO (5)
//  Реализовать следующие элементы управления:
//  -	Периоды рождения объектов – текстовые поля;
//  -	Для задания вероятностей рождения  объектов комбобокс и  список (шаг значений 10%);
//  -	Дополнить интерфейс поясняющими метками.
// ! [-]

// TODO (6)
//  Для хранения генерируемых объектов использовать динамический массив объектов.
//  Для обеспечения доступа всем элементам приложения и создания только одного экземпляра
//  массива объектов используйте структурный паттерн Singleton.
// * [DONE]

// TODO (7)
//  добавить кнопки «Старт» и «Стоп» в панель управления.
//  Они должны запускать и останавливать симуляцию соответственно.
//  Если симуляция остановлена, то кнопка «Стоп» должна блокироваться.
//  Если симуляция идет, то блокируется кнопка «Старт». Клавиши B и E должны функционировать по-прежнему;
// * [DONE]


public class Main extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Habitat view = Habitat.getInstance();
            view.initScene();
            Controller controller = Controller.getInstance();
            controller.initController(view);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}