package domain.gamer.state

import domain.card.Card
import domain.card.CardValue
import domain.card.Shape
import domain.gamer.Dealer
import domain.gamer.cards.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `뽑은 카드를 저장한다`() {
        val card = Card(Shape.HEART, CardValue.EIGHT)
        val dealer = Dealer(Cards(listOf()))
        dealer.pickCard(card)
        assertThat(dealer.cards.getCards()).isEqualTo(listOf(card))
    }

    @Test
    fun `카드 값의 합을 반환한다`() {
        val dealer =
            Dealer(Cards(listOf(Card(Shape.SPADE, CardValue.JACK), Card(Shape.SPADE, CardValue.JACK))))
        assertThat(dealer.calculateCardSum()).isEqualTo(20)
    }

    @Test
    fun `A가 2개 포함되어 있는 카드 값의 합을 반환한다`() {
        val dealer =
            Dealer(Cards(listOf(Card(Shape.SPADE, CardValue.ACE), Card(Shape.HEART, CardValue.ACE))))
        assertThat(dealer.calculateCardSum()).isEqualTo(12)
    }

    @Test
    fun `A가 하나 있고 10 초과 있는 카드 값의 합을 반환한다`() {
        val dealer =
            Dealer(
                Cards(
                    listOf(
                        Card(Shape.SPADE, CardValue.JACK),
                        Card(Shape.HEART, CardValue.THREE),
                        Card(Shape.SPADE, CardValue.ACE)
                    )
                )

            )
        assertThat(dealer.calculateCardSum()).isEqualTo(14)
    }

    @Test
    fun `A가 두개 있고 10 초과 있는 카드 값의 합을 반환한다`() {
        val dealer =
            Dealer(
                Cards(
                    listOf(
                        Card(Shape.SPADE, CardValue.JACK),
                        Card(Shape.SPADE, CardValue.TWO),
                        Card(Shape.HEART, CardValue.ACE),
                        Card(Shape.SPADE, CardValue.ACE)
                    )
                )

            )
        assertThat(dealer.calculateCardSum()).isEqualTo(14)
    }

    @Test
    fun `딜러 카드의 합이 16을 넘었을 경우 false를 반환한다`() {
        val dealer =
            Dealer(Cards(listOf(Card(Shape.SPADE, CardValue.JACK), Card(Shape.SPADE, CardValue.JACK))))
        assertThat(dealer.checkAvailableForPick()).isFalse
    }

    @Test
    fun `딜러 카드의 합이 16을 넘지 않았을 경우 true를 반환한다`() {
        val dealer = Dealer(Cards(listOf(Card(Shape.SPADE, CardValue.JACK))))
        assertThat(dealer.checkAvailableForPick()).isTrue
    }
}
