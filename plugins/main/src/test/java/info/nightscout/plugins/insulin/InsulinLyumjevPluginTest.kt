package info.nightscout.plugins.insulin

import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import info.nightscout.interfaces.Config
import info.nightscout.interfaces.insulin.Insulin
import info.nightscout.interfaces.profile.ProfileFunction
import info.nightscout.interfaces.utils.HardLimits
import info.nightscout.plugins.R
import info.nightscout.rx.bus.RxBus
import info.nightscout.rx.logging.AAPSLogger
import info.nightscout.shared.interfaces.ResourceHelper
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness

@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InsulinLyumjevPluginTest {

    private lateinit var sut: InsulinLyumjevPlugin

    @Mock lateinit var rh: ResourceHelper
    @Mock lateinit var rxBus: RxBus
    @Mock lateinit var profileFunction: ProfileFunction
    @Mock lateinit var aapsLogger: AAPSLogger
    @Mock lateinit var config: Config
    @Mock lateinit var hardLimits: HardLimits

    private var injector: HasAndroidInjector = HasAndroidInjector {
        AndroidInjector {
        }
    }

    @BeforeEach
    fun setup() {
        sut = InsulinLyumjevPlugin(injector, rh, profileFunction, rxBus, aapsLogger, config, hardLimits)
    }

    @Test
    fun `simple peak test`() {
        assertEquals(45, sut.peak)
    }

    @Test
    fun getIdTest() {
        assertEquals(Insulin.InsulinType.OREF_LYUMJEV, sut.id)
    }

    @Test
    fun commentStandardTextTest() {
        `when`(rh.gs(eq(R.string.lyumjev))).thenReturn("Lyumjev")
        assertEquals("Lyumjev", sut.commentStandardText())
    }

    @Test
    fun getFriendlyNameTest() {
        `when`(rh.gs(eq(R.string.lyumjev))).thenReturn("Lyumjev")
        assertEquals("Lyumjev", sut.friendlyName)
    }

}