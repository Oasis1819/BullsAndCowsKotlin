/*
    Bulls And Cows(숫자야구 게임) Kotlin Version - 코틀린은 사랑입니다.
 */

import Retro.Apps.BullsAndCows.Level
import Retro.Apps.BullsAndCows.RandomMaker
import kotlin.system.exitProcess
import java.util.Random as Random //랜덤 클래스 사용
import Retro.Apps.BullsAndCows.ConsoleIO as ConsoleIO //자체 클래스 사용
import Retro.Apps.BullsAndCows.GamePlayInfo as GamePlayInfo //자체 클래스 사용

//프로그램의 도입 부분
fun main(args:Array<String>)
{

    //환영화면
    println("                                                                                                  \n" +
            " ####           ##     ##                    #               #          ###                       \n" +
            "  #  #           #      #                   # #              #         #   #                      \n" +
            "  #  #  #   #    #      #     ###          #   #  # ##    ## #         #       ###   #   #   ###  \n" +
            "  ###   #   #    #      #    #             #   #  ##  #  #  ##         #      #   #  #   #  #     \n" +
            "  #  #  #   #    #      #     ###          #####  #   #  #   #         #      #   #  # # #   ###  \n" +
            "  #  #  #  ##    #      #        #         #   #  #   #  #  ##         #   #  #   #  # # #      # \n" +
            " ####    ## #   ###    ###   ####          #   #  #   #   ## #          ###    ###    # #   ####  \n" +
            " Kotlin Edition / Github : https://github.com/Oasis1819                                             ")


    var GameProfile:GamePlayInfo = GamePlayInfo() //프로파일 생성
    Input_Level@ while(GameProfile.GameLevel == Level.Null) //올바른 입력을 받을 때까지 반복
    {
       println("\n 난이도 :\n * 상(5자리, 9번)\n * 중(4자리, 9번)\n * 하(3자리, 9번)\n\n")
      var LevelText:String = ConsoleIO.ReadConsole("난이도를 입력하세요")

       when(LevelText)
       {
           "상" -> GameProfile.GameLevel = Level.Hard
           "중" -> GameProfile.GameLevel = Level.Middle
          "하" -> GameProfile.GameLevel = Level.Easy
           else -> println(" [*] 올바른 입력이 아닙니다!")
        }
    }

    //정답 만들기
    when(GameProfile.GameLevel)
    {
        Level.Hard -> GameProfile.GameAnswer = RandomMaker.MakeRandom(5)
        Level.Middle ->GameProfile.GameAnswer = RandomMaker.MakeRandom(4)
        Level.Easy -> GameProfile.GameAnswer = RandomMaker.MakeRandom(3)

    }

    println(" // 정답 : ${GameProfile.GameAnswer}") //시연을 위해서 정답 표시

    //문제 풀기
    var Counts_Play = 9 //기회 카운트
    for(i in 1..9) //9번의 기회 부여
    {
        var InputAnswer:String = ConsoleIO.ReadConsole("정답을 입력하세요(${GameProfile.GameAnswer.length}자리 수)")
        if(InputAnswer == GameProfile.GameAnswer) //완전히 일치할 경우(정답일 경우)
        {
            println(" [*] 정답입니다! (${i}회 시도)")
            exitProcess(1) //맞으면 게임 종료
        }
        else
        {
            //스캔 함수를 돌려서 얼마나 맞았는지 반환
            println(" [*] ${Scan_Answer(GameProfile, InputAnswer)}")
        }
        Counts_Play = Counts_Play - 1 //기회 카운트 감소
        println(" [*] ${Counts_Play}회 남았습니다!")

    }
    println("\n [*] 게임 오버! 정답은 '${GameProfile.GameAnswer}'입니다!")

}

//값 스캔 함수
fun Scan_Answer(GameProfile:GamePlayInfo ,Answer:String):String
{

    //스트라이크 개수
    var StrikeCount:Int = 0
    for(i in 0..Answer.length - 1)
    {
        //같은자리 값을 확인하기 위해 같은 자리의 문자값을 구해옴
        var UserAnswer:String = Answer[i].toString() //유저의 정답
        var RealAnswer:String = GameProfile.GameAnswer[i].toString() //진짜 답

        //유저값이랑 정답이랑 같으면(스트라이크면)
        if(UserAnswer == RealAnswer)
        {
            StrikeCount = StrikeCount + 1
        }
    }

    //볼 개수
    var BallCount:Int = 0
    for(i in 0..Answer.length - 1)
    {
        //같은자리 값을 확인하기 위해 같은 자리의 문자값을 구해옴
        var UserAnswer:String = Answer[i].toString() //유저의 정답
        var RealAnswer:String = GameProfile.GameAnswer[i].toString() //진짜 답

        //유저값이랑 정답이랑 다르지만, 전체 안에는 포함되며, 포함되는 숫자 개수와 같은경우(볼)
        if(UserAnswer != RealAnswer && GameProfile.GameAnswer.contains(UserAnswer) && CountingChar(Answer, UserAnswer) == CountingChar(GameProfile.GameAnswer, RealAnswer))
        {
            BallCount = BallCount + 1
        }
    }


    return "${StrikeCount} 스트라이크 ${BallCount}볼입니다!"
}

//스캔 함수를 위한 포함 숫자 개수 카운팅
fun CountingChar(Str:String, FindChar:String):Int
{
    var ResultCount:Int = 0
    for(i in 0..Str.length - 1)
    {
        if(Str[i].toString() == FindChar) //맞을경우
        {
            ResultCount = ResultCount + 1
        }

    }

    return ResultCount

}