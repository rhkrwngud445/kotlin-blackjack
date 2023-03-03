package controller

import domain.BlackjackGame
import view.InputView
import view.OutputView

class BlackjackController() {

    fun startGame() {
        val names = InputView.inputPlayerNames()
        val blackjackGame = BlackjackGame(names)
        OutputView.printDivideCard(names)
        OutputView.printDealerSettingCard(blackjackGame.dealerState.cards[0])
        OutputView.printParticipantsCards(blackjackGame.playersStates)
        requestPickCard(names, blackjackGame)
    }

    private fun requestPickCard(names: List<String>, blackjackGame: BlackjackGame) {
        names.forEach { name ->
            repeatPickCard(blackjackGame, name)
        }
    }

    private fun repeatPickCard(blackjackGame: BlackjackGame, name: String) {
        while (!blackjackGame.checkBurst(name)) {
            val answer = validatePickAnswer(name)
            if (answer == "y") blackjackGame.pickCard(name)
            if (answer == "n") return
            OutputView.printParticipantCards(name, blackjackGame.playersStates.getValue(name).cards)
        }
    }

    private fun validatePickAnswer(name: String): String {
        val answer = InputView.inputRepeatGetCard(name)
        return answer ?: validatePickAnswer(name)
    }
}