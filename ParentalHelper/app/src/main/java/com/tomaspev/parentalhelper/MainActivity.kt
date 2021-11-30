package com.tomaspev.parentalhelper

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// Clase que permite la diferenciacion de los tipos de proveedor para iniciar sesión
enum class  ProviderType {
    BASIC,
    GOOGLE,
    FACEBOOK
}

class MainActivity : AppCompatActivity() {
    // Registros
    private lateinit var registroAdapter: RegistroAdapter
    private lateinit var dataListR: List<Registro>
    // Contenidos
    private lateinit var dataListC: List<Contenido>
    // Destacados
    private lateinit var destacadoAdapter: DestacadoAdapter
    // Nuevos Contenidos
    private lateinit var contenidoNuevoAdapter: ContenidoNuevoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Registros ============================================================================================================

        val recyclerViewR = findViewById<RecyclerView>(R.id.rv_registros)
        recyclerViewR.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        registroAdapter = RegistroAdapter(applicationContext)
        recyclerViewR.adapter = registroAdapter

        dataListR = listOf(
            Registro("Manuel", "12/04/2019", "H", false, 20),
            Registro("Jose", "15/07/2018", "H", true, 40),
            Registro("Martina", "23/09/2017", "M", false, 80)
        )

        registroAdapter.setDataList(dataListR)

        // Contenidos ===========================================================================================================

        // Adapter Contenidos Destacados
        val recyclerViewD = findViewById<RecyclerView>(R.id.rv_destacados)
        recyclerViewD.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        destacadoAdapter = DestacadoAdapter(applicationContext)
        recyclerViewD.adapter = destacadoAdapter

        registroAdapter.setDataList(dataListR)

        // Adapter Contenidos Nuevos
        val recyclerViewC = findViewById<RecyclerView>(R.id.rv_nuevos_contenidos)
        recyclerViewC.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        contenidoNuevoAdapter = ContenidoNuevoAdapter(applicationContext)
        recyclerViewC.adapter = contenidoNuevoAdapter

        registroAdapter.setDataList(dataListR)
        // Lista de Contenidos
        dataListC = listOf(
            //contenido nuevo

            Contenido("Experimento Vaso de Agua y Carta de Naipe","3","Interacción y Comprensión del Entorno","Exploración del Entorno Natural","Medio", "Experimentar con materiales cotidianos tales como: agua y aire describiendo lo observado.","-.-","En esta actividad los niños podrán aprender como funcionan las fuerzas de gravedad y tensión superficial del agua","¿Qué materiales usar? \n" +
        " Una carta de naipe. Un vaso de vidrio lleno de agua hasta el borde y un recipiente para contener el agua (por si el experimento falle) \n" +
                " ¿Qué hace el adulto? \n" +
                " El adulto invita al niño/a a hacer un experimento motivándolo con la siguiente pregunta: ¿Cómo podemos voltear un vaso lleno de agua sin que se caiga el agua? Invitándolo a llenar un vaso de vidrio con agua hasta el borde y depositar el vaso en una fuente que pueda retener el agua por si el experimento falla, luego invitara al niño/a a poner la carta de naipe sobre el vaso y a dar vuelta el vaso viendo que ocurre. Preguntando al niño/a por que ocurre lo que ocurre. \n" +
                "Explicación científica: sobre la carta actúan dos fuerzas, el peso del agua y la presión atmosférica, la presión atmosférica es mayor y empuja la carta hacia arriba. Aunque por poco si movemos la carta segura se cae. \n" +
                " ¿Qué hace el niño/a? \n" +
                " El niño/a seguirá las instrucciones del adulto y luego se preguntarán por qué ocurre eso. Dando posibles respuestas a las interrogantes del adulto \n" +
                " ¿Qué debo observar? \n" +
                "El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Por qué ocurre eso?, buscando posibles respuestas. Luego hablaran si les gusto la experiencia si le gustaría hacer nuevos experimentos otro día. ","Nuevo")

        ,Contenido("Ver video de Cuento Monstruo Triste Monstruo Feliz","2","Desarrollo Personal y Social","Identidad y Autonomía","Medio", "Reconocer en sí mismo, en otras personas y en personajes de cuentos, emociones tales como: tristeza, miedo, alegría, pena y rabia.","https://www.youtube.com/watch?v=B9YMaeehOmk ","En esta actividad los niños podrán desarrollar sus habilidades de crecimiento personal e identidad","¿Qué materiales usar? \n" +
                " Video del cuento monstruo tiste monstruo feliz. https://www.youtube.com/watch?v=B9YMaeehOmk Materiales para el dibujo\n" +
                " ¿Qué hace el adulto? \n" +
                "El adulto invita al niño/a a ver un video del cuento “Monstruo Triste Monstruo feliz”, luego de ver el video conversaran en torno a las cosas que lo hacen ponerse triste, las cosas que lo hacen poner enojado, las cosas que lo hacen sentir cariñoso, las cosas que lo hacen sentir triste, las cosas que lo hacen sentir miedo, las cosas que lo hacen sentir furioso, las cosas que lo hacen sentir divertido. \n" +
                "¿Qué hace el niño/a? \n" +
                " El niño/a conversara sobre las cosas que lo hacen feliz, triste, enojado, cariñoso, miedoso, furioso y divertido. Luego si tiene lápices de colores o\n" +
                "tempera, podrá hacer una máscara del monstruo de la emoción que siente en ese momento contándole a su familia por qué se siente así.\n" +
                " ¿Qué debo observar? \n" +
                "El adulto junto al niño/a puede evaluar el trabajo con preguntas tales como: ¿Cómo te sientes hoy ?, ¿Cómo te sientes con mayor frecuencia?,\n" +
                "¿Por qué te sientes así?\n","Nuevo")

        ,Contenido("Hacer barquitos de papel y jugar en fuente de agua","3","Interacción y Comprensión del Entorno","Pensamiento Matemático ","Medio", "Experimentar con materiales cotidianos tales como: agua y aire describiendo lo observado.","-.-","En esta actividad los niños podrán elavorar un barco de papel y luego descubrir los principios de flotabilidad","¿Qué materiales usar? \n" +
                " Una carta de naipe. Un vaso de vidrio lleno de agua hasta el borde y un recipiente para contener el agua (por si el experimento falle) \n" +
                " ¿Qué hace el adulto? \n" +
                " El adulto invita al niño/a a hacer un experimento motivándolo con la siguiente pregunta: ¿Cómo podemos voltear un vaso lleno de agua sin que se caiga el agua? Invitándolo a llenar un vaso de vidrio con agua hasta el borde y depositar el vaso en una fuente que pueda retener el agua por si el experimento falla, luego invitara al niño/a a poner la carta de naipe sobre el vaso y a dar vuelta el vaso viendo que ocurre. Preguntando al niño/a por que ocurre lo que ocurre. \n" +
                "Explicación científica: sobre la carta actúan dos fuerzas, el peso del agua y la presión atmosférica, la presión atmosférica es mayor y empuja la carta hacia arriba. Aunque por poco si movemos la carta segura se cae. \n" +
                " ¿Qué hace el niño/a? \n" +
                " El niño/a seguirá las instrucciones del adulto y luego se preguntarán por qué ocurre eso. Dando posibles respuestas a las interrogantes del adulto \n" +
                " ¿Qué debo observar? \n" +
                "El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Por qué ocurre eso?, buscando posibles respuestas. Luego hablaran si les gusto la experiencia si le gustaría hacer nuevos experimentos otro día. ","Nuevo")




            //contenido destacado

        ,Contenido("Experimento Vaso de Agua y Carta de Naipe","3","Interacción y Comprensión del Entorno","Exploración del Entorno Natural","Medio", "Experimentar con materiales cotidianos tales como: agua y aire describiendo lo observado.","-.-","En esta actividad los niños podrán aprender como funcionan las fuerzas de gravedad y tensión superficial del agua","¿Qué materiales usar? \n" +
                    " Una carta de naipe. Un vaso de vidrio lleno de agua hasta el borde y un recipiente para contener el agua (por si el experimento falle) \n" +
                    " ¿Qué hace el adulto? \n" +
                    " El adulto invita al niño/a a hacer un experimento motivándolo con la siguiente pregunta: ¿Cómo podemos voltear un vaso lleno de agua sin que se caiga el agua? Invitándolo a llenar un vaso de vidrio con agua hasta el borde y depositar el vaso en una fuente que pueda retener el agua por si el experimento falla, luego invitara al niño/a a poner la carta de naipe sobre el vaso y a dar vuelta el vaso viendo que ocurre. Preguntando al niño/a por que ocurre lo que ocurre. \n" +
                    "Explicación científica: sobre la carta actúan dos fuerzas, el peso del agua y la presión atmosférica, la presión atmosférica es mayor y empuja la carta hacia arriba. Aunque por poco si movemos la carta segura se cae. \n" +
                    " ¿Qué hace el niño/a? \n" +
                    " El niño/a seguirá las instrucciones del adulto y luego se preguntarán por qué ocurre eso. Dando posibles respuestas a las interrogantes del adulto \n" +
                    " ¿Qué debo observar? \n" +
                    "El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Por qué ocurre eso?, buscando posibles respuestas. Luego hablaran si les gusto la experiencia si le gustaría hacer nuevos experimentos otro día. ","Destacado")

        ,Contenido("Ver video de Cuento Monstruo Triste Monstruo Feliz","2","Desarrollo Personal y Social","Identidad y Autonomía","Medio", "Reconocer en sí mismo, en otras personas y en personajes de cuentos, emociones tales como: tristeza, miedo, alegría, pena y rabia.","https://www.youtube.com/watch?v=B9YMaeehOmk ","En esta actividad los niños podrán desarrollar sus habilidades de crecimiento personal e identidad","¿Qué materiales usar? \n" +
                " Video del cuento monstruo tiste monstruo feliz. https://www.youtube.com/watch?v=B9YMaeehOmk Materiales para el dibujo\n" +
                " ¿Qué hace el adulto? \n" +
                "El adulto invita al niño/a a ver un video del cuento “Monstruo Triste Monstruo feliz”, luego de ver el video conversaran en torno a las cosas que lo hacen ponerse triste, las cosas que lo hacen poner enojado, las cosas que lo hacen sentir cariñoso, las cosas que lo hacen sentir triste, las cosas que lo hacen sentir miedo, las cosas que lo hacen sentir furioso, las cosas que lo hacen sentir divertido. \n" +
                "¿Qué hace el niño/a? \n" +
                " El niño/a conversara sobre las cosas que lo hacen feliz, triste, enojado, cariñoso, miedoso, furioso y divertido. Luego si tiene lápices de colores o\n" +
                "tempera, podrá hacer una máscara del monstruo de la emoción que siente en ese momento contándole a su familia por qué se siente así.\n" +
                " ¿Qué debo observar? \n" +
                "El adulto junto al niño/a puede evaluar el trabajo con preguntas tales como: ¿Cómo te sientes hoy ?, ¿Cómo te sientes con mayor frecuencia?,\n" +
                "¿Por qué te sientes así?\n","Destacado")

        ,Contenido("Hacer barquitos de papel y jugar en fuente de agua","3","Interacción y Comprensión del Entorno","Pensamiento Matemático ","Medio", "Experimentar con materiales cotidianos tales como: agua y aire describiendo lo observado.","-.-","En esta actividad los niños podrán elavorar un barco de papel y luego descubrir los principios de flotabilidad","¿Qué materiales usar? \n" +
                " Una carta de naipe. Un vaso de vidrio lleno de agua hasta el borde y un recipiente para contener el agua (por si el experimento falle) \n" +
                " ¿Qué hace el adulto? \n" +
                " El adulto invita al niño/a a hacer un experimento motivándolo con la siguiente pregunta: ¿Cómo podemos voltear un vaso lleno de agua sin que se caiga el agua? Invitándolo a llenar un vaso de vidrio con agua hasta el borde y depositar el vaso en una fuente que pueda retener el agua por si el experimento falla, luego invitara al niño/a a poner la carta de naipe sobre el vaso y a dar vuelta el vaso viendo que ocurre. Preguntando al niño/a por que ocurre lo que ocurre. \n" +
                "Explicación científica: sobre la carta actúan dos fuerzas, el peso del agua y la presión atmosférica, la presión atmosférica es mayor y empuja la carta hacia arriba. Aunque por poco si movemos la carta segura se cae. \n" +
                " ¿Qué hace el niño/a? \n" +
                " El niño/a seguirá las instrucciones del adulto y luego se preguntarán por qué ocurre eso. Dando posibles respuestas a las interrogantes del adulto \n" +
                " ¿Qué debo observar? \n" +
                "El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Por qué ocurre eso?, buscando posibles respuestas. Luego hablaran si les gusto la experiencia si le gustaría hacer nuevos experimentos otro día. ","Destacado")


        )

        // Lista Contenidos Destacados
        val dataListD = dataListC.filter { it.tipo == "Destacado" }
        destacadoAdapter.setDataList(dataListD)

        // Lista Contenidos Nuevos
        val dataListNC = dataListC.filter { it.tipo == "Nuevo" }
        contenidoNuevoAdapter.setDataList(dataListNC)

        // Resto del código =====================================================================================================

        tv_todos_registros.setOnClickListener {
            val intent = Intent(this, ListadoRegistros::class.java)
            startActivity(intent)
        }
        tv_todos_nuevos.setOnClickListener {
            val intent = Intent(this, NewContent::class.java)
            startActivity(intent)
        }

        // Parte tomas - verificación y mantener sesión iniciada
        val bundle = intent.extras                               // Variable que rescata los extras que trae el Intent
        val email = bundle?.getString("email")              // Variable que rescata el correo
        val provider = bundle?.getString("provider")        // Variable que rescata el provider

        setup(email ?: "", provider ?: "")         // Carga la funcion y sus datos provenientes de otros activity

        // VARIABLE QUE PERMITE MANTENER LA SESION INICIADA AL UTILIZAR PREFERENCIAS
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()
    }

    // FUNCION QUE PERMITE CERRAR LA SESION EN FIREBASE Y VACIAR PREFS
    private fun setup(email: String, provider: String) {
        /*LogOutButton.setOnClickListener {

            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            if (provider == ProviderType.FACEBOOK.name) {
                LoginManager.getInstance().logOut()
            }

            FirebaseAuth.getInstance().signOut()
            val home = Intent(this, Login::class.java)
            startActivity(home)
        }*/
    }
}