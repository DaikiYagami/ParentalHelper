package com.tomaspev.parentalhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DetalleRegistro : AppCompatActivity() {
    private lateinit var progresoContenidoAdapter: ProgresoContenidoAdapter
    private lateinit var dataList: List<ProgresoContenido>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_registro)

        // Código para mostrar los datos del registro seleccionado ==============================================================>>>

        // Se recibe el objeto registro pasado por el intent
        val registro = intent.getSerializableExtra("registro") as Registro

        // Se identifican los campos mostrados en el layout
        val nombre: TextView = findViewById(R.id.tv_detalle_registro_nombre)
        val edad: TextView = findViewById(R.id.tv_detalle_registro_edad)
        val cumple: TextView = findViewById(R.id.tv_detalle_registro_cumple)
        val genero: TextView = findViewById(R.id.tv_detalle_registro_genero)

        // Se le asignan los valores guardados en el objeto registro a los campos del layout
        nombre.text = registro.nombre
        edad.text = edad(registro.cumple, fechaHoy()).toString()
        cumple.text = registro.cumple
        genero.text = registro.genero

        // Esto muestra el progreso en los contenidos asociados a este registro =================================================>>>
        val recyclerView = findViewById<RecyclerView>(R.id.rv_detalle_registro_contenidos)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 1, GridLayoutManager.VERTICAL, false)
        progresoContenidoAdapter = ProgresoContenidoAdapter(applicationContext)
        recyclerView.adapter = progresoContenidoAdapter

        dataList = listOf(
            ProgresoContenido(Contenido("Experimento Vaso de Agua y Carta de Naipe","3","Interacción y Comprensión del Entorno","Exploración del Entorno Natural","Medio", "Experimentar con materiales cotidianos tales como: agua y aire describiendo lo observado.","-.-","En esta actividad los niños podrán aprender como funcionan las fuerzas de gravedad y tensión superficial del agua","¿Qué materiales usar? \n" +
                    " Una carta de naipe. Un vaso de vidrio lleno de agua hasta el borde y un recipiente para contener el agua (por si el experimento falle) \n" +
                    " ¿Qué hace el adulto? \n" +
                    " El adulto invita al niño/a a hacer un experimento motivándolo con la siguiente pregunta: ¿Cómo podemos voltear un vaso lleno de agua sin que se caiga el agua? Invitándolo a llenar un vaso de vidrio con agua hasta el borde y depositar el vaso en una fuente que pueda retener el agua por si el experimento falla, luego invitara al niño/a a poner la carta de naipe sobre el vaso y a dar vuelta el vaso viendo que ocurre. Preguntando al niño/a por que ocurre lo que ocurre. \n" +
                    "Explicación científica: sobre la carta actúan dos fuerzas, el peso del agua y la presión atmosférica, la presión atmosférica es mayor y empuja la carta hacia arriba. Aunque por poco si movemos la carta segura se cae. \n" +
                    " ¿Qué hace el niño/a? \n" +
                    " El niño/a seguirá las instrucciones del adulto y luego se preguntarán por qué ocurre eso. Dando posibles respuestas a las interrogantes del adulto \n" +
                    " ¿Qué debo observar? \n" +
                    "El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Por qué ocurre eso?, buscando posibles respuestas. Luego hablaran si les gusto la experiencia si le gustaría hacer nuevos experimentos otro día. ","Nuevo"), 60),
            ProgresoContenido(Contenido("Ver video de Cuento Monstruo Triste Monstruo Feliz","2","Desarrollo Personal y Social","Identidad y Autonomía","Medio", "Reconocer en sí mismo, en otras personas y en personajes de cuentos, emociones tales como: tristeza, miedo, alegría, pena y rabia.","https://www.youtube.com/watch?v=B9YMaeehOmk ","En esta actividad los niños podrán desarrollar sus habilidades de crecimiento personal e identidad","¿Qué materiales usar? \n" +
                    " Video del cuento monstruo tiste monstruo feliz. https://www.youtube.com/watch?v=B9YMaeehOmk Materiales para el dibujo\n" +
                    " ¿Qué hace el adulto? \n" +
                    "El adulto invita al niño/a a ver un video del cuento “Monstruo Triste Monstruo feliz”, luego de ver el video conversaran en torno a las cosas que lo hacen ponerse triste, las cosas que lo hacen poner enojado, las cosas que lo hacen sentir cariñoso, las cosas que lo hacen sentir triste, las cosas que lo hacen sentir miedo, las cosas que lo hacen sentir furioso, las cosas que lo hacen sentir divertido. \n" +
                    "¿Qué hace el niño/a? \n" +
                    " El niño/a conversara sobre las cosas que lo hacen feliz, triste, enojado, cariñoso, miedoso, furioso y divertido. Luego si tiene lápices de colores o\n" +
                    "tempera, podrá hacer una máscara del monstruo de la emoción que siente en ese momento contándole a su familia por qué se siente así.\n" +
                    " ¿Qué debo observar? \n" +
                    "El adulto junto al niño/a puede evaluar el trabajo con preguntas tales como: ¿Cómo te sientes hoy ?, ¿Cómo te sientes con mayor frecuencia?,\n" +
                    "¿Por qué te sientes así?\n","Nuevo"), 20),
            ProgresoContenido(Contenido("Hacer barquitos de papel y jugar en fuente de agua","3","Interacción y Comprensión del Entorno","Pensamiento Matemático ","Medio", "Experimentar con materiales cotidianos tales como: agua y aire describiendo lo observado.","-.-","En esta actividad los niños podrán elavorar un barco de papel y luego descubrir los principios de flotabilidad","¿Qué materiales usar? \n" +
                    " Una carta de naipe. Un vaso de vidrio lleno de agua hasta el borde y un recipiente para contener el agua (por si el experimento falle) \n" +
                    " ¿Qué hace el adulto? \n" +
                    " El adulto invita al niño/a a hacer un experimento motivándolo con la siguiente pregunta: ¿Cómo podemos voltear un vaso lleno de agua sin que se caiga el agua? Invitándolo a llenar un vaso de vidrio con agua hasta el borde y depositar el vaso en una fuente que pueda retener el agua por si el experimento falla, luego invitara al niño/a a poner la carta de naipe sobre el vaso y a dar vuelta el vaso viendo que ocurre. Preguntando al niño/a por que ocurre lo que ocurre. \n" +
                    "Explicación científica: sobre la carta actúan dos fuerzas, el peso del agua y la presión atmosférica, la presión atmosférica es mayor y empuja la carta hacia arriba. Aunque por poco si movemos la carta segura se cae. \n" +
                    " ¿Qué hace el niño/a? \n" +
                    " El niño/a seguirá las instrucciones del adulto y luego se preguntarán por qué ocurre eso. Dando posibles respuestas a las interrogantes del adulto \n" +
                    " ¿Qué debo observar? \n" +
                    "El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Por qué ocurre eso?, buscando posibles respuestas. Luego hablaran si les gusto la experiencia si le gustaría hacer nuevos experimentos otro día. ","Nuevo"), 40),


            ProgresoContenido(Contenido("Experimento Vaso de Agua y Carta de Naipe","3","Interacción y Comprensión del Entorno","Exploración del Entorno Natural","Medio", "Experimentar con materiales cotidianos tales como: agua y aire describiendo lo observado.","-.-","En esta actividad los niños podrán aprender como funcionan las fuerzas de gravedad y tensión superficial del agua","¿Qué materiales usar? \n" +
                    " Una carta de naipe. Un vaso de vidrio lleno de agua hasta el borde y un recipiente para contener el agua (por si el experimento falle) \n" +
                    " ¿Qué hace el adulto? \n" +
                    " El adulto invita al niño/a a hacer un experimento motivándolo con la siguiente pregunta: ¿Cómo podemos voltear un vaso lleno de agua sin que se caiga el agua? Invitándolo a llenar un vaso de vidrio con agua hasta el borde y depositar el vaso en una fuente que pueda retener el agua por si el experimento falla, luego invitara al niño/a a poner la carta de naipe sobre el vaso y a dar vuelta el vaso viendo que ocurre. Preguntando al niño/a por que ocurre lo que ocurre. \n" +
                    "Explicación científica: sobre la carta actúan dos fuerzas, el peso del agua y la presión atmosférica, la presión atmosférica es mayor y empuja la carta hacia arriba. Aunque por poco si movemos la carta segura se cae. \n" +
                    " ¿Qué hace el niño/a? \n" +
                    " El niño/a seguirá las instrucciones del adulto y luego se preguntarán por qué ocurre eso. Dando posibles respuestas a las interrogantes del adulto \n" +
                    " ¿Qué debo observar? \n" +
                    "El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Por qué ocurre eso?, buscando posibles respuestas. Luego hablaran si les gusto la experiencia si le gustaría hacer nuevos experimentos otro día. ","Destacado"), 60),
            ProgresoContenido(Contenido("Ver video de Cuento Monstruo Triste Monstruo Feliz","2","Desarrollo Personal y Social","Identidad y Autonomía","Medio", "Reconocer en sí mismo, en otras personas y en personajes de cuentos, emociones tales como: tristeza, miedo, alegría, pena y rabia.","https://www.youtube.com/watch?v=B9YMaeehOmk ","En esta actividad los niños podrán desarrollar sus habilidades de crecimiento personal e identidad","¿Qué materiales usar? \n" +
                    " Video del cuento monstruo tiste monstruo feliz. https://www.youtube.com/watch?v=B9YMaeehOmk Materiales para el dibujo\n" +
                    " ¿Qué hace el adulto? \n" +
                    "El adulto invita al niño/a a ver un video del cuento “Monstruo Triste Monstruo feliz”, luego de ver el video conversaran en torno a las cosas que lo hacen ponerse triste, las cosas que lo hacen poner enojado, las cosas que lo hacen sentir cariñoso, las cosas que lo hacen sentir triste, las cosas que lo hacen sentir miedo, las cosas que lo hacen sentir furioso, las cosas que lo hacen sentir divertido. \n" +
                    "¿Qué hace el niño/a? \n" +
                    " El niño/a conversara sobre las cosas que lo hacen feliz, triste, enojado, cariñoso, miedoso, furioso y divertido. Luego si tiene lápices de colores o\n" +
                    "tempera, podrá hacer una máscara del monstruo de la emoción que siente en ese momento contándole a su familia por qué se siente así.\n" +
                    " ¿Qué debo observar? \n" +
                    "El adulto junto al niño/a puede evaluar el trabajo con preguntas tales como: ¿Cómo te sientes hoy ?, ¿Cómo te sientes con mayor frecuencia?,\n" +
                    "¿Por qué te sientes así?\n","Destacado"), 20),
            ProgresoContenido(Contenido("Hacer barquitos de papel y jugar en fuente de agua","3","Interacción y Comprensión del Entorno","Pensamiento Matemático ","Medio", "Experimentar con materiales cotidianos tales como: agua y aire describiendo lo observado.","-.-","En esta actividad los niños podrán elavorar un barco de papel y luego descubrir los principios de flotabilidad","¿Qué materiales usar? \n" +
                    " Una carta de naipe. Un vaso de vidrio lleno de agua hasta el borde y un recipiente para contener el agua (por si el experimento falle) \n" +
                    " ¿Qué hace el adulto? \n" +
                    " El adulto invita al niño/a a hacer un experimento motivándolo con la siguiente pregunta: ¿Cómo podemos voltear un vaso lleno de agua sin que se caiga el agua? Invitándolo a llenar un vaso de vidrio con agua hasta el borde y depositar el vaso en una fuente que pueda retener el agua por si el experimento falla, luego invitara al niño/a a poner la carta de naipe sobre el vaso y a dar vuelta el vaso viendo que ocurre. Preguntando al niño/a por que ocurre lo que ocurre. \n" +
                    "Explicación científica: sobre la carta actúan dos fuerzas, el peso del agua y la presión atmosférica, la presión atmosférica es mayor y empuja la carta hacia arriba. Aunque por poco si movemos la carta segura se cae. \n" +
                    " ¿Qué hace el niño/a? \n" +
                    " El niño/a seguirá las instrucciones del adulto y luego se preguntarán por qué ocurre eso. Dando posibles respuestas a las interrogantes del adulto \n" +
                    " ¿Qué debo observar? \n" +
                    "El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Por qué ocurre eso?, buscando posibles respuestas. Luego hablaran si les gusto la experiencia si le gustaría hacer nuevos experimentos otro día. ","Destacado"), 40),

        )

        progresoContenidoAdapter.setDataList(dataList)
    }
}