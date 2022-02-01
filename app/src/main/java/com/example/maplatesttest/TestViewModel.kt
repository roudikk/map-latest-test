import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@Suppress("EXPERIMENTAL_API_USAGE")
class TestViewModel(
    private val scope: CoroutineScope
) {

    private val mutableStateFlow = MutableStateFlow("Me!")
    val stateFlow: StateFlow<String> = mutableStateFlow.asStateFlow()

    private val testStringFlow = MutableSharedFlow<String>()

    init {
        testStringFlow
            .mapLatest { "Hello: $it" }
            .onEach { mutableStateFlow.value = it }
            .launchIn(scope)
    }

    fun onValue(value: String) {
        scope.launch {
            testStringFlow.emit(value)
        }
    }
}