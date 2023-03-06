package domain.judge

import domain.gamer.Dealer
import domain.gamer.Player

class Referee(private val dealer: Dealer, private val players: List<Player>) {

    fun judgePlayersResult(): Map<String, Result> = mutableMapOf<String, Result>().apply {
        players.forEach {
            this[it.name] = judgePlayerResult(it)
        }
    }

    private fun judgePlayerResult(player: Player): Result {
        val playerSum = player.calculateCardSum()
        val dealerSum = dealer.calculateCardSum()

        return when {
            playerSum > CARD_SUM_MAX_VALUE ||
                (dealer.checkBlackjack() && !player.checkBlackjack()) ||
                dealerSum.checkPlayerLossCondition(
                    playerSum
                ) -> Result.LOSS

            playerSum > dealerSum || dealerSum > CARD_SUM_MAX_VALUE || (player.checkBlackjack() && !dealer.checkBlackjack()) -> Result.WIN
            else -> Result.DRAW
        }
    }

    private fun Int.checkPlayerLossCondition(playerSum: Int) = this > playerSum && this <= CARD_SUM_MAX_VALUE

    companion object {
        const val CARD_SUM_MAX_VALUE = 21
    }
}
