package Retro.Apps.BullsAndCows

class ConsoleIO
{

    //Static Methods
    companion object {

        //안내 텍스트와 함께 입력받음
        fun ReadConsole(InfoText:String):String
        {
            print(" ${InfoText} >>")
            return readLine()!!
        }



    }
}