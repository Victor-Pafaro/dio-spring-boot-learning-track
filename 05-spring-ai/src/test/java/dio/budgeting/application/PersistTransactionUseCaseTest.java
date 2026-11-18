package dio.budgeting.application;

import dio.budgeting.application.input.PersistTransactionInput;
import dio.budgeting.domain.Category;
import dio.budgeting.domain.Transaction;
import dio.budgeting.domain.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersistTransactionUseCaseTest {

    @Mock
    private TransactionRepository transactionRepository;

    private PersistTransactionUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new PersistTransactionUseCase(transactionRepository);
    }

    @Test
    void shouldPersistTransactionWhenInputIsValid() {
        var input = new PersistTransactionInput("Compras no mercado", 4500L, Category.GROCERIES);
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var output = useCase.execute(input);

        assertThat(output).isNotNull();
        assertThat(output.description()).isEqualTo("Compras no mercado");
        assertThat(output.value()).isEqualTo(4500.0);
        assertThat(output.category()).isEqualTo("GROCERIES");
        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    void shouldThrowExceptionWhenAmountIsZeroOrNegative() {
        var zeroInput = new PersistTransactionInput("Café", 0L, Category.GROCERIES);
        var negativeInput = new PersistTransactionInput("Café", -100L, Category.GROCERIES);

        assertThatThrownBy(() -> useCase.execute(zeroInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("O valor da transação deve ser maior que zero.");

        assertThatThrownBy(() -> useCase.execute(negativeInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("O valor da transação deve ser maior que zero.");

        verify(transactionRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsBlank() {
        var blankInput = new PersistTransactionInput("   ", 1500L, Category.GROCERIES);

        assertThatThrownBy(() -> useCase.execute(blankInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("A descrição da transação não pode ser vazia.");

        verify(transactionRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenCategoryIsNull() {
        var nullCategoryInput = new PersistTransactionInput("Uber", 2500L, null);

        assertThatThrownBy(() -> useCase.execute(nullCategoryInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("A categoria da transação é obrigatória.");

        verify(transactionRepository, never()).save(any());
    }
}
