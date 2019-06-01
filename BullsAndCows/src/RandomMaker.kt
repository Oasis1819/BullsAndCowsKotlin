package Retro.Apps.BullsAndCows

import java.util.*

class RandomMaker
{

    //Static Methods
    companion object {

        fun MakeRandom(Size:Int):String
        {
            val NumberList:Array<String> = arrayOf<String>("0","1", "2", "3","4","5","6","7","8","9") //숫자 리스트값
            var ResultValue:String = ""

            for(i in 0..Size - 1)
            {
                var RandomChar:String = NumberList[Random().nextInt(NumberList.size)]
                while(ResultValue.contains(RandomChar)) //중복되지 않는 값이 나올때까지 반복
                {
                    RandomChar = NumberList[Random().nextInt(NumberList.size)]
                }

                //중복되지 않는 값이 나오면
                ResultValue = ResultValue + RandomChar //랜덤값 추가

            }

            return ResultValue
        }


    }
}