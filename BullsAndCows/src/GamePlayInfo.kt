package Retro.Apps.BullsAndCows

public class GamePlayInfo
{
    var GameLevel:Level = Level.Null //게임 난이도
    var GameAnswer:String = "" //숫자야구 정답 값


}

enum class Level
{
    Hard,Middle,Easy,Null
}