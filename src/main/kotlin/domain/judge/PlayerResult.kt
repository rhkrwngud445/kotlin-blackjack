package domain.judge

import domain.money.Money

data class PlayerResultInfo(val result: Result, val money: Money) {

    fun calculateRevenue(isBlackJack: Boolean): Int {
        return when (result) {
            Result.WIN -> if (isBlackJack) calculateBlackJackMoney() else money.value
            Result.LOSS -> calculateLossMoney()
            Result.DRAW -> calculateDrawMoney()
        }
    }

    private fun calculateBlackJackMoney(): Int = (money.value * 1.5).toInt()

    private fun calculateLossMoney(): Int = money.value * -1

    private fun calculateDrawMoney(): Int = 0
}
