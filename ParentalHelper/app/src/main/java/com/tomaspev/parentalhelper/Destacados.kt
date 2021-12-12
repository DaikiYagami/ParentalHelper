package com.tomaspev.parentalhelper

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_destacados.*

class Destacados : AppCompatActivity() {
    private lateinit var destacadoAdapter: ListadoDestacadoAdapter
    private lateinit var dataListC: List<Contenido>

    private lateinit var prefs: SharedPreferences.Editor
    private var bundle: Bundle? = null
    private var email: String? = null
    private var provider: String? = null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_basico, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destacados)

        setSupportActionBar(toolbar_contenido_destacado)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_lista_destacado)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        destacadoAdapter = ListadoDestacadoAdapter(applicationContext)
        recyclerView.adapter = destacadoAdapter

        dataListC = listOf(
            //contenido nuevo

            Contenido("Experimento Vaso de Agua y Carta de Naipe","3","Interacción y Comprensión \n" + " del Entorno","Exploración del Entorno Natural","Medio", "Experimentar con materiales cotidianos tales como: agua y aire describiendo lo observado.","g615EoOIlug","En esta actividad los niños podrán aprender como funcionan las fuerzas de gravedad y tensión superficial del agua","¿Qué materiales usar? \n" +
                    " Una carta de naipe. Un vaso de vidrio lleno de agua hasta el borde y un recipiente para contener el agua (por si el experimento falle) \n" +
                    " ¿Qué hace el adulto? \n" +
                    " El adulto invita al niño/a a hacer un experimento motivándolo con la siguiente pregunta: ¿Cómo podemos voltear un vaso lleno de agua sin que se caiga el agua? Invitándolo a llenar un vaso de vidrio con agua hasta el borde y depositar el vaso en una fuente que pueda retener el agua por si el experimento falla, luego invitara al niño/a a poner la carta de naipe sobre el vaso y a dar vuelta el vaso viendo que ocurre. Preguntando al niño/a por que ocurre lo que ocurre. \n" +
                    "Explicación científica: sobre la carta actúan dos fuerzas, el peso del agua y la presión atmosférica, la presión atmosférica es mayor y empuja la carta hacia arriba. Aunque por poco si movemos la carta segura se cae. \n" +
                    " ¿Qué hace el niño/a? \n" +
                    " El niño/a seguirá las instrucciones del adulto y luego se preguntarán por qué ocurre eso. Dando posibles respuestas a las interrogantes del adulto \n" +
                    " ¿Qué debo observar? \n" +
                    "El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Por qué ocurre eso?, buscando posibles respuestas. Luego hablaran si les gusto la experiencia si le gustaría hacer nuevos experimentos otro día. ","Nuevo")

            ,Contenido("Ver video de Cuento Monstruo Triste Monstruo Feliz","2","Desarrollo Personal y Social","Identidad y Autonomía","Medio", "Reconocer en sí mismo, en otras personas y en personajes de cuentos, emociones tales como: tristeza, miedo, alegría, pena y rabia.","IuEYPS9vTak","En esta actividad los niños podrán desarrollar sus habilidades de crecimiento personal e identidad","¿Qué materiales usar? \n" +
                    " Video del cuento monstruo tiste monstruo feliz. https://www.youtube.com/watch?v=B9YMaeehOmk Materiales para el dibujo\n" +
                    " ¿Qué hace el adulto? \n" +
                    "El adulto invita al niño/a a ver un video del cuento “Monstruo Triste Monstruo feliz”, luego de ver el video conversaran en torno a las cosas que lo hacen ponerse triste, las cosas que lo hacen poner enojado, las cosas que lo hacen sentir cariñoso, las cosas que lo hacen sentir triste, las cosas que lo hacen sentir miedo, las cosas que lo hacen sentir furioso, las cosas que lo hacen sentir divertido. \n" +
                    "¿Qué hace el niño/a? \n" +
                    " El niño/a conversara sobre las cosas que lo hacen feliz, triste, enojado, cariñoso, miedoso, furioso y divertido. Luego si tiene lápices de colores o\n" +
                    "tempera, podrá hacer una máscara del monstruo de la emoción que siente en ese momento contándole a su familia por qué se siente así.\n" +
                    " ¿Qué debo observar? \n" +
                    "El adulto junto al niño/a puede evaluar el trabajo con preguntas tales como: ¿Cómo te sientes hoy ?, ¿Cómo te sientes con mayor frecuencia?,\n" +
                    "¿Por qué te sientes así?\n","Nuevo")

            ,Contenido("Hacer barquitos de papel y jugar en fuente de agua","3","Interacción y Comprensión \n" + " del Entorno","Pensamiento Matemático ","Medio", "Experimentar con materiales cotidianos tales como: agua y aire describiendo lo observado.","Laj6AuGri2w","En esta actividad los niños podrán elavorar un barco de papel y luego descubrir los principios de flotabilidad","¿Qué materiales usar? \n" +
                    " Una carta de naipe. Un vaso de vidrio lleno de agua hasta el borde y un recipiente para contener el agua (por si el experimento falle) \n" +
                    " ¿Qué hace el adulto? \n" +
                    " El adulto invita al niño/a a hacer un experimento motivándolo con la siguiente pregunta: ¿Cómo podemos voltear un vaso lleno de agua sin que se caiga el agua? Invitándolo a llenar un vaso de vidrio con agua hasta el borde y depositar el vaso en una fuente que pueda retener el agua por si el experimento falla, luego invitara al niño/a a poner la carta de naipe sobre el vaso y a dar vuelta el vaso viendo que ocurre. Preguntando al niño/a por que ocurre lo que ocurre. \n" +
                    "Explicación científica: sobre la carta actúan dos fuerzas, el peso del agua y la presión atmosférica, la presión atmosférica es mayor y empuja la carta hacia arriba. Aunque por poco si movemos la carta segura se cae. \n" +
                    " ¿Qué hace el niño/a? \n" +
                    " El niño/a seguirá las instrucciones del adulto y luego se preguntarán por qué ocurre eso. Dando posibles respuestas a las interrogantes del adulto \n" +
                    " ¿Qué debo observar? \n" +
                    "El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Por qué ocurre eso?, buscando posibles respuestas. Luego hablaran si les gusto la experiencia si le gustaría hacer nuevos experimentos otro día. ","Nuevo")

            ,Contenido("Historia Compartida","3","Comunicación Integral","Lenguaje Verbal ","Medio", "Manifestar interés por descubrir el contenido de textos, a través de la, la escucha atenta, la expresión de ideas y la formulación de preguntas.","2x_Kr_EH7b0","En esta actividad los niños podrán practicar su lenguaje verbal.","¿Qué materiales usar? \n" +
                    " Jugar con toda la familia\n" +
                    " ¿Qué hace el adulto? \n" +
                    " El adulto invitará al niño/a a jugar haciendo una historia compartida dando como elemento central un personaje ejemplo: había una vez un sapito verde que nadaba en la laguna…. y cada uno de los integrantes de la familia seguirá agregando algo a la historia hasta terminarla.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    " El niño/a participara agregando mas contenido a la historia, con la participación de su familia\n" +
                    "¿Qué debo observar? \n" +
                    "El adulto junto al niño puede evaluar el trabajo con preguntas tales como: ¿Qué es lo que más te gusto de la historia?, ¿Qué parte de la historia inventaste tu?, ¿Qué parte de la historia invento tu hermano?, ¿Cómo termino la historia?","Nuevo")

            ,Contenido("Hacer Caballo de Palo con Calcetín","3","Desarrollo Personal y Social","Identidad y autonomía ","Medio", "Representar sus pensamientos y experiencias, atribuyendo significados a objetos o elementos de su entorno, usando la imaginación en situaciones de juego","OUA8T2IPIsM","En esta actividad los niños podrán elaborar un caballo de palo junto a sus padres","¿Qué materiales usar? \n" +
                    " Calcetín viejo, restos de tela para el relleno, 2 botones, hilo aguja palo de escoba viejo. Pegamento.\n" +
                    "¿Qué hace el adulto? \n" +
                    "El adulto armara la cabeza de caballo con un calcetín viejo relleno con mopa o trozos de tela y la pegara a un palo de escoba amarrándolo o pegándolo. Luego invitara al niño/a a decorarlo pegándole los ojos con botones, las clines con trozos de hilo y cociéndole una boca bordada, luego les pondrán una rienda amarrada y el niño/a podrá jugar por la casa jugando en el caballito de palo.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    " El niño/a junto al adulto, confeccionará el caballito de palo, armándolo y luego podrá jugar por la casa andando en el caballito de palo.\n" +
                    "¿Qué debo observar? \n" +
                    "El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Qué te parece el juguete que hicimos?, ¿Qué materiales usaste?, ¿Cómo se llama tu caballo?, ¿Dónde iras en tu caballito de palo? ","Nuevo")

            ,Contenido("JUGANDO CON LA LLUVIA","3","Interacción y Comprensión del Entorno","Exploración del Entorno Natural ","Sala Cuna", "Manifestar curiosidad y asombro por algunos elementos, situaciones y fenómenos que ocurren en su entorno natural cercano, tales como: arena, lluvia, viento, entre otros","_xdf4kU6RrE","En esta actividad los niños podrán interactuar con el fenómeno natural de la lluvia.","¿Qué materiales usar? \n" +
                    "Paraguas, ropa de abrigo (parka y calzado adecuado). Un día lluvioso para poder realizar la actividad.\n" +
                    " ¿Qué hace el adulto? \n" +
                    " El adulto debe estar atento a las condiciones del clima y aprovechar un día de lluvia para salir junto a sus hijo/a al patio o a la ventana a ver la lluvia. Si no hay cuarentena, puede salir al patio o fuera de casa a disfrutar de la lluvia cuando recién comienza en un paseo cercano al hogar. El adulto media con preguntas y/o afirmaciones como: ¿Conoces la lluvia?, ¡Mira, siente como cae en el rostro!, ¡saltemos los charcos para no mojarnos!, ¿Qué color tiene el cielo? ¿Te gustó el paseo? El adulto escucha sus respuestas.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    " El niño y la niña observará la naturaleza durante el paseo o salida al patio, jugando con su madre a saltar los charcos. Observando y comentando los cambios que observa como: día más oscuro, el color del cielo, la temperatura, entre otros.\n" +
                    "¿Qué debo observar? \n" +
                    "Las expresiones verbales y no verbales del niño/a en relación a la lluvia. Observar su agrado por la experiencia en la naturaleza.","Nuevo")

            ,Contenido("Veo, veo ¿qué ves?","3","Interacción y Comprensión del Entorno","Exploración del Entorno Natural ","Sala Cuna", "Reconocer algunos elementos representativos de su entorno natural, tales como: animales, plantas, ríos, cerros, desierto.","qGMY3PEbGE0","En esta actividad los niños podrán visualizar y diferenciar los seres vivos del reino animal.","¿Qué materiales usar? \n" +
                    " Fotos captadas en su entorno natural cercano de algunos animales como: Perro, gato, pájaros (paloma, tregil, codorniz, aguilucho), u otros cercanos. Videos breves de imágenes de los animales de su entorno natural cercano. Lápices y hoja\n" +
                    " ¿Qué hace el adulto? \n" +
                    " Previo a la experiencia debe sacar fotografías y grabar videos de animales de su entorno natural cercano utilizando su celular. Luego el adulto invita al niño/a a sentarse en el sillón para mostrarle algo que tiene en su celular. Antes de invitar al niño/a, debe mantener el living ordenado y sin la televisión encendida, para evitar distracciones. El adulto le dirá al niño/a que le quiere mostrar unas fotos que tomó y se las muestra una a una preguntando ¿Qué animal es?, ¿Lo conoces?. Lo invita a ver los videos y escucha sus respuestas, finalmente lo invita a dibujar el animal favorito.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    " Disfrutará de la experiencia, observando las fotografías y videos de animales de su entorno cercano, nombrándolos, realizando su sonido, imitando sus movimientos y dibujando lo que más le gustó.\n" +
                    "¿Qué debo observar? \n" +
                    "Que el niño/a logre reconocer los animales que se le presentan en las fotografías y videos, dentro de sus posibilidades de lenguaje verbal y corporal.","Nuevo")

            ,Contenido("Jugando a las sonrisas","3","Desarrollo Personal y Social","Identidad y autonomía ","Sala Cuna", "Expresar vocal, gestual o corporalmente distintas necesidades o emociones (alegría, miedo, pena, entre otras)","2vDqG65_cGg","En esta actividad los niños podrán interactuar con títeres hechos en casa.","¿Qué materiales usar? \n" +
                    " Ropa Cómoda para el bebé, que lo mantenga seguro en cuanto a temperatura y movilidad. Un calcetín que debemos transformar en títere, como el de la imagen o según los materiales y creatividad de la familia, se recomienda utilizar elementos en desuso.\n" +
                    " ¿Qué hace el adulto? \n" +
                    " El adulto toma al niño/a entre sus brazos y lo recuesta sobre la cama o sillón, el cual debe estar ordenado previamente y disponer una frazada o mantita bajo de él/ella, para su comodidad. Luego el adulto juega con el niño/a, utilizando el títere de mano, imitando voces y haciendo cosquillas al bebé, junto con masajes en el cuerpo a través del uso de títere. Puede cantarle una canción por medio del títere. Le dice palabras de cariño y festeja sus interacciones.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    "El niño y la niña observa el títere, escuchando y tocándolo dentro de sus posibilidades motoras y de exploración. Manifestando lo que este le produce, como: alegría, asombro, y capta su atención.\n" +
                    "¿Qué debo observar? \n" +
                    "Las expresiones vocales y gestuales del niño/a en relación al juego que realiza su madre con el títere, intentando esta hacerle cosquillas y permitir un momento placentero de alegría al bebé.","Nuevo")

            ,Contenido("Mensajes de apoyo","3","Desarrollo Personal y Social","Convivencia y ciudadanía","Medio", "Participar en actividades grupales, conversando, cooperando, entre otras","prHuClGHtmY","En esta actividad los niños podrán entender y analizar el contexto de la pandemia actual.","¿Qué materiales usar? \n" +
                    "Hojas blancas, de colores, etc. Lápices de colores, a cera, plumones, etc. \n" +
                    "¿Qué hace el adulto? \n" +
                    " El adulto invita al niño/a a conversar sobre la situación actual del virus, con relación a las personas que están enfermas y que deben ser aisladas en sus casas o estar en los hospitales. Conversarán si tiene algún familiar enfermo, vecino, u otra persona que le puedan enviar por las redes sociales un mensaje de apoyo.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    "El niño/a junto al adulto podrán crear mensajes inspiradores para que las personas contagiadas con el virus puedan estar de buen ánimo y tranquilas para mejorarse. El niño/a podrá decir si los mensajes, los crean en carteles, cartas, mensajes, entre otras iniciativas.\n" +
                    " ¿Qué debo observar?\n" +
                    "  El adulto junto al niño/a puede evaluar el trabajo con preguntas tales como: ¿Te gusto hacer la actividad?, ¿Por qué crees que estos mensajes ayudaran a las personas en su enfermedad?, ¿A quiénes se los enviaran?, entre otras.","Nuevo")

            ,Contenido("Circuito con tubos","3","Desarrollo Personal y Social","Corporalidad y Movimiento","Medio", "Perfeccionar su coordinación visomotora fina, a través del uso de diversos objetos.","9URGRMp4Z-g","En esta actividad los niños podrán visualizar y diferenciar los seres vivos del reino animal.","¿Qué materiales usar? \n" +
                    "Tubos de papel de diferentes tamaños (confort, nova, etc.), cajas de cartón o plásticas. Pompones o pelotas pequeñas. Cinta masking, scotch, u otra cinta adhesiva.\n" +
                    "¿Qué hace el adulto? \n" +
                    "El adulto invita al niño/a a buscar un lugar de la casa adecuado para crear un circuito, que contemple uniones y diversas direcciones (inclinaciones, rectas, curvas).\n" +
                    " ¿Qué hace el niño/a? \n" +
                    "El niño/a con ayuda del adulto podrá crear un circuito inclinando los tubos con ayuda de las cajas, por el cual entre y salga la pelota pequeña. Los niños/as podrán unir los tubos con otros, creando sus propios circuitos y jugando a que la pelota pequeña ruede por los tubos sin salir del circuito.\n" +
                    " ¿Qué debo observar?\n" +
                    "  El adulto junto al niño/a puede evaluar el trabajo con preguntas tales como: ¿Te gusto crear el circuito?, ¿Unir los tubos fue fácil o difícil?, ¿Qué otros circuitos se podrían hacer?, entre otras.","Nuevo")

            ,Contenido("Ciencia en tu ensalada","3","Interacción y Comprensión del Entorno","Exploración del Entorno Natural","Medio", "Manifestar interés y asombro por diversas situaciones, explorando, observando, describiendo, entre otros.","nS1QBVF39-Q","En esta actividad los niños podrán experimentar con el crecimiento natural de vegetales.","¿Qué materiales usar? \n" +
                    "Vaso o frasco Tallo de lechuga, cebollín, apio o repollo Agua\n" +
                    "¿Qué hace el adulto? \n" +
                    "El adulto invita al niño/a a participar de un trabajo en ciencias. Jugarán a ser científicos, desarrollando una actividad en la cual si dejas algunas verduras en el agua vuelven a crecer. Se fomentará la creatividad e investigación científica en el niño/a. El adulto apoyará con las explicaciones de la actividad científica (Material de apoyo).\n" +
                    " ¿Qué hace el niño/a? \n" +
                    "El niño/a podrá explorar las verduras que tenga y armar sus cultivos con las raíces, experimentando, explorando y a medida que pasen los días describiendo los cambios observados.\n" +
                    " ¿Qué debo observar?\n" +
                    "  El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Qué verduras usaste en el experimento de cultivos?, ¿Sabías que las plantas almacenan nutrientes?, ¿Por qué es necesario ubicar tu cultivo en un lugar que reciba la luz del sol? entre otras.","Nuevo")

            ,Contenido("Disfrutemos los sonidos","3","Comunicación Integral","Lenguajes artísticos","Sala cuna", "Manifestar interés por los sonidos, colores, luminosidad de su entorno, respondiendo a través de diversas formas, tales como balbuceos, gestos, sonrisas, entre otros","jBN8ZQfb7iM","En esta actividad los niños podrán con sonidos de forma controlada.","¿Qué materiales usar? \n" +
                    "FABRICAR CAJA DE SONIDOS: será una caja con distintos sonajeros: Botella pequeña con agua. Botella pequeña rellena con pequeñas piedras sellada. Botella con distintos rellenos livianos y coloridos, según lo que disponga en el hogar, asegurarse que estén selladas ante la manipulación del niño/a. Un paño o género para tapar la caja\n" +
                    "¿Qué hace el adulto? \n" +
                    "El adulto toma entre sus brazos a su hijo/a, le habla suavemente y lo invita a ver una sorpresa que le ha preparado en la cama (orden y aseo del dormitorio realizado previamente). El adulto hace sonar un sonajero, sin que el niño/a lo vea y lo invita a ver que hay bajo el paño, descubriendo una caja llena de botellas rellenas con distintos elementos.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    "El niño/a observará el contenido de la caja y explorará con sus manos, boca, mirada cada elemento, agitándolo, produciendo diversos sonidos.\n" +
                    " ¿Qué debo observar?\n" +
                    "  Ver las formas en que el niño/a responde a sus exploraciones y sonidos que produce con los elementos. Balbuceos, gestos, sonrisas, tiempo que destina a la exploración, movimientos corporales, entre otros.","Nuevo")

            ,Contenido("Jugando a la actuación","3","Comunicación Integral","Lenguajes artísticos","Medio", "Expresar corporalmente sensaciones y emociones experimentando con mímica y juegos teatrales.","NnCYZc7huC4","En esta actividad los niños podrán teatralizar situaciones.","¿Qué materiales usar? \n" +
                    "Ropa, Artículos: sombreros, corbatas, collares, lentes, instrumentos musicales, entre otros materiales que tengan en casa.\n" +
                    "¿Qué hace el adulto? \n" +
                    "El tutor fomentará un juego de actuación con el niño/a, buscarán ropa y otros elementos para disfrazarse. El tutor con el niño/a podrán disfrutar del placer de hacer este juego.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    "El niño/a podrá elegir un personaje para representar: amigos, familiares, artistas, entre otros. Podrá utilizar elementos para la caracterización del personaje. El niño/a podrá expresar sentimientos, pensamientos, integrando el movimiento y la palabra.\n" +
                    " ¿Qué debo observar?\n" +
                    "El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿A quienes imitaste?, ¿Te gusto el juego de imitación?, ¿Quiénes participaron del juego?, entre otras.","Nuevo")

            ,Contenido("Clasificar objetos de la casa, según USO","3","Interacción y Comprensión del Entorno","Pensamiento Matemático","Medio", "Experimentar con diversos objetos, estableciendo relaciones al clasificar por dos atributos a la vez (uso y color).","8I29hSt5QCs","En esta actividad los niños podrán clasificar objetos","¿Qué materiales usar? \n" +
                    "Objetos tecnológicos: celulares, cámaras, computador, televisores, entre otros. Objetos para cocinar: ollas, sartenes, tostador, entre otros., Objetos decorativos: chiches, cuadros, fotos, entre otros.\n" +
                    "¿Qué hace el adulto? \n" +
                    "El tutor invitará al niño/a a agrupar objetos tecnológicos, decorativos, de cocina y otros que el desee integrar. Luego se le explicara al niño/a que jugarán a clasificar este material, según su uso en la casa y color. \n" +
                    "¿Qué hace el niño/a? \n" +
                    "El niño/a deberá agrupar objetos de la casa. El niño/a podrá clasificar los objetos según uso y color, además puede clasificar utilizando otras cualidades de los objetos, por ejemplo, según tamaño, forma, peso, entre otros.\n" +
                    " ¿Qué debo observar?\n" +
                    "  El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Te gusto el juego de clasificación?, ¿Con que materiales de la casa trabajaste?, ¿Qué cualidades de los objetos usaste para clasificar?, entre otras.","Nuevo")

            ,Contenido("Jugando con mi cuerpo y juguetes","3","Interacción y Comprensión del Entorno","Pensamiento Matemático","Medio", "Describir la posición de objetos y personas, respecto de un punto u objetos de referencia, empleando conceptos de ubicación y distancia tales como: dentro-fuera, encima-debajo, cerca-lejos.","BN_LWZko2Ps","En esta actividad los niños podrán interactuar y aprender los significados de cerca y lejos.","¿Qué materiales usar? \n" +
                    "Cajas de cartón o plásticas (dentro de lo posible grandes). Frazadas. Juguetes de los niños/as\n" +
                    "¿Qué hace el adulto? \n" +
                    "El tutor invitará al niño/a a elegir un espacio de la casa para jugar, luego ubicarán las cajas y frazadas. El tutor invitara al niño a jugar con su cuerpo, solicitándoles que se ubique dentro-fuera de la caja, encima-debajo de la frazada, cerca-lejos de ambos elementos. Luego las mismas acciones el tutor las solicitará con sus juguetes.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    "El niño/a podrá jugar con su cuerpo a las relaciones espaciales con los objetos: dentro-fuera, encima-debajo, cerca-lejos. El niño/a podrá jugar a estas relaciones (dentro-fuera, encima-debajo, cerca-lejos) con sus juguetes. \n" +
                    "¿Qué debo observar?\n" +
                    "El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Fue entretenido el juego?, ¿Quiénes jugaron contigo?, ¿Qué acciones fueron más difíciles de hacer?, entre otras.","Nuevo")

            ,Contenido("Cuidados que debemos tener con algunos objetos en la casa","3","Desarrollo Personal y Social","Convivencia y ciudadanía","Medio", "Identificar objetos, comportamientos y situaciones de riesgo que pueden atentar contra su seguridad, bienestar y el de los demás.","I1ynPyE0Osw","En esta actividad los niños podrán aprender respecto de los peligros en los hogares.","¿Qué materiales usar? \n" +
                    "Objetos de riesgo de la casa. Papel: pliego de cartulina, kraf, hojas de block. Lápices, plumones, entre otros.\n" +
                    "¿Qué hace el adulto? \n" +
                    "El tutor invitará al niño/a a conversar sobre algunos objetos que hay en la casa y con los cuales deben tener cuidado (se realizará una lista de estos objetos).\n" +
                    " ¿Qué hace el niño/a? \n" +
                    "El tutor invitará al niño/a a identificar estos objetos, por ejemplo, cuchillos, fósforos, plancha, tijeras, agua caliente, enchufes, botones, útiles de aseo, cocina, entre otros. Junto al tutor podrán crear un afiche identificando los objetos de riesgo, uso correcto, cuidados y precauciones que se deben tener en cuenta para evitar algún accidente. Este afiche lo pueden compartir vía WhatsApp u otro medio de comunicación con sus amigos, compañeros, familiares. compañeros, familiares.\n" +
                    " ¿Qué debo observar?\n" +
                    "El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Cuáles son los objetos de la casa, con los cuales debemos tener cuidado?, ¿Qué medidas preventivas acordaron con el tutor?, ¿Por qué es importante compartir el afiche con sus amigos?, entre otras.","Nuevo")

            ,Contenido("Mi identidad","3","Desarrollo Personal y Social","Identidad y Autonomía","Medio", "Comunicar algunos rasgos de su identidad, como nombre, características corporales, género y otros.","srmiSiJHCxQ","En esta actividad los niños podrán complementar su sentir consigo mismos.","¿Qué materiales usar? \n" +
                    "Espejo (ideal a la altura del niño). 1 trozo de cartón, block, cartulina, etc. Lana, pitilla, \uF0B7 Botones grandes, tapitas. Lápices, plumones. Colafria u otro pegamento. Otros materiales que tengan para la representación del niño\n" +
                    "¿Qué hace el adulto? \n" +
                    "El tutor invitará al niño/a observarse en un espejo, luego conversarán sobre rasgos de su identidad: nombre, género, rasgos físicos y lo que le gusta hacer. El tutor le debe expresar al niño/a su afecto, valoración y aceptación.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    "El niño/a podrá decir su nombre, sexo, intereses y características corporales. El niño/a podrá representarse a sí mismo por medio de una obra plástica, utilizando los materiales que sean de su interés.\n" +
                    " ¿Qué debo observar?\n" +
                    "  El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Cuál es tu nombre?, ¿Tú género?, ¿Nos puedes contar de tus características físicas?, ¿Qué te gusta hacer?, entre otras.","Nuevo")




            //contenido destacado

            ,Contenido("Mi identidad","3","Desarrollo Personal y Social","Identidad y Autonomía","Medio", "Comunicar algunos rasgos de su identidad, como nombre, características corporales, género y otros.","srmiSiJHCxQ","En esta actividad los niños podrán complementar su sentir consigo mismos.","¿Qué materiales usar? \n" +
                    "Espejo (ideal a la altura del niño). 1 trozo de cartón, block, cartulina, etc. Lana, pitilla, \uF0B7 Botones grandes, tapitas. Lápices, plumones. Colafria u otro pegamento. Otros materiales que tengan para la representación del niño\n" +
                    "¿Qué hace el adulto? \n" +
                    "El tutor invitará al niño/a observarse en un espejo, luego conversarán sobre rasgos de su identidad: nombre, género, rasgos físicos y lo que le gusta hacer. El tutor le debe expresar al niño/a su afecto, valoración y aceptación.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    "El niño/a podrá decir su nombre, sexo, intereses y características corporales. El niño/a podrá representarse a sí mismo por medio de una obra plástica, utilizando los materiales que sean de su interés.\n" +
                    " ¿Qué debo observar?\n" +
                    "  El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Cuál es tu nombre?, ¿Tú género?, ¿Nos puedes contar de tus características físicas?, ¿Qué te gusta hacer?, entre otras.","Destacado")


            ,Contenido("Cuidados que debemos tener con algunos objetos en la casa","3","Desarrollo Personal y Social","Convivencia y ciudadanía","Medio", "Identificar objetos, comportamientos y situaciones de riesgo que pueden atentar contra su seguridad, bienestar y el de los demás.","I1ynPyE0Osw","En esta actividad los niños podrán aprender respecto de los peligros en los hogares.","¿Qué materiales usar? \n" +
                    "Objetos de riesgo de la casa. Papel: pliego de cartulina, kraf, hojas de block. Lápices, plumones, entre otros.\n" +
                    "¿Qué hace el adulto? \n" +
                    "El tutor invitará al niño/a a conversar sobre algunos objetos que hay en la casa y con los cuales deben tener cuidado (se realizará una lista de estos objetos).\n" +
                    " ¿Qué hace el niño/a? \n" +
                    "El tutor invitará al niño/a a identificar estos objetos, por ejemplo, cuchillos, fósforos, plancha, tijeras, agua caliente, enchufes, botones, útiles de aseo, cocina, entre otros. Junto al tutor podrán crear un afiche identificando los objetos de riesgo, uso correcto, cuidados y precauciones que se deben tener en cuenta para evitar algún accidente. Este afiche lo pueden compartir vía WhatsApp u otro medio de comunicación con sus amigos, compañeros, familiares. compañeros, familiares.\n" +
                    " ¿Qué debo observar?\n" +
                    "El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Cuáles son los objetos de la casa, con los cuales debemos tener cuidado?, ¿Qué medidas preventivas acordaron con el tutor?, ¿Por qué es importante compartir el afiche con sus amigos?, entre otras.","Destacado")


            ,Contenido("Jugando con mi cuerpo y juguetes","3","Interacción y Comprensión del Entorno","Pensamiento Matemático","Medio", "Describir la posición de objetos y personas, respecto de un punto u objetos de referencia, empleando conceptos de ubicación y distancia tales como: dentro-fuera, encima-debajo, cerca-lejos.","BN_LWZko2Ps","En esta actividad los niños podrán interactuar y aprender los significados de cerca y lejos.","¿Qué materiales usar? \n" +
                    "Cajas de cartón o plásticas (dentro de lo posible grandes). Frazadas. Juguetes de los niños/as\n" +
                    "¿Qué hace el adulto? \n" +
                    "El tutor invitará al niño/a a elegir un espacio de la casa para jugar, luego ubicarán las cajas y frazadas. El tutor invitara al niño a jugar con su cuerpo, solicitándoles que se ubique dentro-fuera de la caja, encima-debajo de la frazada, cerca-lejos de ambos elementos. Luego las mismas acciones el tutor las solicitará con sus juguetes.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    "El niño/a podrá jugar con su cuerpo a las relaciones espaciales con los objetos: dentro-fuera, encima-debajo, cerca-lejos. El niño/a podrá jugar a estas relaciones (dentro-fuera, encima-debajo, cerca-lejos) con sus juguetes. \n" +
                    "¿Qué debo observar?\n" +
                    "El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Fue entretenido el juego?, ¿Quiénes jugaron contigo?, ¿Qué acciones fueron más difíciles de hacer?, entre otras.","Destacado")


            ,Contenido("Clasificar objetos de la casa, según USO","3","Interacción y Comprensión del Entorno","Pensamiento Matemático","Medio", "Experimentar con diversos objetos, estableciendo relaciones al clasificar por dos atributos a la vez (uso y color).","8I29hSt5QCs","En esta actividad los niños podrán clasificar objetos","¿Qué materiales usar? \n" +
                    "Objetos tecnológicos: celulares, cámaras, computador, televisores, entre otros. Objetos para cocinar: ollas, sartenes, tostador, entre otros., Objetos decorativos: chiches, cuadros, fotos, entre otros.\n" +
                    "¿Qué hace el adulto? \n" +
                    "El tutor invitará al niño/a a agrupar objetos tecnológicos, decorativos, de cocina y otros que el desee integrar. Luego se le explicara al niño/a que jugarán a clasificar este material, según su uso en la casa y color. \n" +
                    "¿Qué hace el niño/a? \n" +
                    "El niño/a deberá agrupar objetos de la casa. El niño/a podrá clasificar los objetos según uso y color, además puede clasificar utilizando otras cualidades de los objetos, por ejemplo, según tamaño, forma, peso, entre otros.\n" +
                    " ¿Qué debo observar?\n" +
                    "  El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Te gusto el juego de clasificación?, ¿Con que materiales de la casa trabajaste?, ¿Qué cualidades de los objetos usaste para clasificar?, entre otras.","Destacado")


            ,Contenido("Jugando a la actuación","3","Comunicación Integral","Lenguajes artísticos","Medio", "Expresar corporalmente sensaciones y emociones experimentando con mímica y juegos teatrales.","NnCYZc7huC4","En esta actividad los niños podrán teatralizar situaciones.","¿Qué materiales usar? \n" +
                    "Ropa, Artículos: sombreros, corbatas, collares, lentes, instrumentos musicales, entre otros materiales que tengan en casa.\n" +
                    "¿Qué hace el adulto? \n" +
                    "El tutor fomentará un juego de actuación con el niño/a, buscarán ropa y otros elementos para disfrazarse. El tutor con el niño/a podrán disfrutar del placer de hacer este juego.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    "El niño/a podrá elegir un personaje para representar: amigos, familiares, artistas, entre otros. Podrá utilizar elementos para la caracterización del personaje. El niño/a podrá expresar sentimientos, pensamientos, integrando el movimiento y la palabra.\n" +
                    " ¿Qué debo observar?\n" +
                    "El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿A quienes imitaste?, ¿Te gusto el juego de imitación?, ¿Quiénes participaron del juego?, entre otras.","Destacado")



            ,Contenido("Disfrutemos los sonidos","3","Comunicación Integral","Lenguajes artísticos","Sala cuna", "Manifestar interés por los sonidos, colores, luminosidad de su entorno, respondiendo a través de diversas formas, tales como balbuceos, gestos, sonrisas, entre otros","jBN8ZQfb7iM","En esta actividad los niños podrán con sonidos de forma controlada.","¿Qué materiales usar? \n" +
                    "FABRICAR CAJA DE SONIDOS: será una caja con distintos sonajeros: Botella pequeña con agua. Botella pequeña rellena con pequeñas piedras sellada. Botella con distintos rellenos livianos y coloridos, según lo que disponga en el hogar, asegurarse que estén selladas ante la manipulación del niño/a. Un paño o género para tapar la caja\n" +
                    "¿Qué hace el adulto? \n" +
                    "El adulto toma entre sus brazos a su hijo/a, le habla suavemente y lo invita a ver una sorpresa que le ha preparado en la cama (orden y aseo del dormitorio realizado previamente). El adulto hace sonar un sonajero, sin que el niño/a lo vea y lo invita a ver que hay bajo el paño, descubriendo una caja llena de botellas rellenas con distintos elementos.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    "El niño/a observará el contenido de la caja y explorará con sus manos, boca, mirada cada elemento, agitándolo, produciendo diversos sonidos.\n" +
                    " ¿Qué debo observar?\n" +
                    "  Ver las formas en que el niño/a responde a sus exploraciones y sonidos que produce con los elementos. Balbuceos, gestos, sonrisas, tiempo que destina a la exploración, movimientos corporales, entre otros.","Destacado")


            ,Contenido("Ciencia en tu ensalada","3","Interacción y Comprensión del Entorno","Exploración del Entorno Natural","Medio", "Manifestar interés y asombro por diversas situaciones, explorando, observando, describiendo, entre otros.","nS1QBVF39-Q","En esta actividad los niños podrán experimentar con el crecimiento natural de vegetales.","¿Qué materiales usar? \n" +
                    "Vaso o frasco Tallo de lechuga, cebollín, apio o repollo Agua\n" +
                    "¿Qué hace el adulto? \n" +
                    "El adulto invita al niño/a a participar de un trabajo en ciencias. Jugarán a ser científicos, desarrollando una actividad en la cual si dejas algunas verduras en el agua vuelven a crecer. Se fomentará la creatividad e investigación científica en el niño/a. El adulto apoyará con las explicaciones de la actividad científica (Material de apoyo).\n" +
                    " ¿Qué hace el niño/a? \n" +
                    "El niño/a podrá explorar las verduras que tenga y armar sus cultivos con las raíces, experimentando, explorando y a medida que pasen los días describiendo los cambios observados.\n" +
                    " ¿Qué debo observar?\n" +
                    "  El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Qué verduras usaste en el experimento de cultivos?, ¿Sabías que las plantas almacenan nutrientes?, ¿Por qué es necesario ubicar tu cultivo en un lugar que reciba la luz del sol? entre otras.","Destacado")


            ,Contenido("Circuito con tubos","3","Desarrollo Personal y Social","Corporalidad y Movimiento","Medio", "Perfeccionar su coordinación visomotora fina, a través del uso de diversos objetos.","9URGRMp4Z-g","En esta actividad los niños podrán visualizar y diferenciar los seres vivos del reino animal.","¿Qué materiales usar? \n" +
                    "Tubos de papel de diferentes tamaños (confort, nova, etc.), cajas de cartón o plásticas. Pompones o pelotas pequeñas. Cinta masking, scotch, u otra cinta adhesiva.\n" +
                    "¿Qué hace el adulto? \n" +
                    "El adulto invita al niño/a a buscar un lugar de la casa adecuado para crear un circuito, que contemple uniones y diversas direcciones (inclinaciones, rectas, curvas).\n" +
                    " ¿Qué hace el niño/a? \n" +
                    "El niño/a con ayuda del adulto podrá crear un circuito inclinando los tubos con ayuda de las cajas, por el cual entre y salga la pelota pequeña. Los niños/as podrán unir los tubos con otros, creando sus propios circuitos y jugando a que la pelota pequeña ruede por los tubos sin salir del circuito.\n" +
                    " ¿Qué debo observar?\n" +
                    "  El adulto junto al niño/a puede evaluar el trabajo con preguntas tales como: ¿Te gusto crear el circuito?, ¿Unir los tubos fue fácil o difícil?, ¿Qué otros circuitos se podrían hacer?, entre otras.","Destacado")

            ,Contenido("Mensajes de apoyo","3","Desarrollo Personal y Social","Convivencia y ciudadanía","Medio", "Participar en actividades grupales, conversando, cooperando, entre otras","prHuClGHtmY","En esta actividad los niños podrán entender y analizar el contexto de la pandemia actual.","¿Qué materiales usar? \n" +
                    "Hojas blancas, de colores, etc. Lápices de colores, a cera, plumones, etc. \n" +
                    "¿Qué hace el adulto? \n" +
                    " El adulto invita al niño/a a conversar sobre la situación actual del virus, con relación a las personas que están enfermas y que deben ser aisladas en sus casas o estar en los hospitales. Conversarán si tiene algún familiar enfermo, vecino, u otra persona que le puedan enviar por las redes sociales un mensaje de apoyo.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    "El niño/a junto al adulto podrán crear mensajes inspiradores para que las personas contagiadas con el virus puedan estar de buen ánimo y tranquilas para mejorarse. El niño/a podrá decir si los mensajes, los crean en carteles, cartas, mensajes, entre otras iniciativas.\n" +
                    " ¿Qué debo observar?\n" +
                    "  El adulto junto al niño/a puede evaluar el trabajo con preguntas tales como: ¿Te gusto hacer la actividad?, ¿Por qué crees que estos mensajes ayudaran a las personas en su enfermedad?, ¿A quiénes se los enviaran?, entre otras.","Destacado")

            ,Contenido("Jugando a las sonrisas","3","Desarrollo Personal y Social","Identidad y autonomía ","Sala Cuna", "Expresar vocal, gestual o corporalmente distintas necesidades o emociones (alegría, miedo, pena, entre otras)","2vDqG65_cGg","En esta actividad los niños podrán interactuar con títeres hechos en casa.","¿Qué materiales usar? \n" +
                    " Ropa Cómoda para el bebé, que lo mantenga seguro en cuanto a temperatura y movilidad. Un calcetín que debemos transformar en títere, como el de la imagen o según los materiales y creatividad de la familia, se recomienda utilizar elementos en desuso.\n" +
                    " ¿Qué hace el adulto? \n" +
                    " El adulto toma al niño/a entre sus brazos y lo recuesta sobre la cama o sillón, el cual debe estar ordenado previamente y disponer una frazada o mantita bajo de él/ella, para su comodidad. Luego el adulto juega con el niño/a, utilizando el títere de mano, imitando voces y haciendo cosquillas al bebé, junto con masajes en el cuerpo a través del uso de títere. Puede cantarle una canción por medio del títere. Le dice palabras de cariño y festeja sus interacciones.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    "El niño y la niña observa el títere, escuchando y tocándolo dentro de sus posibilidades motoras y de exploración. Manifestando lo que este le produce, como: alegría, asombro, y capta su atención.\n" +
                    "¿Qué debo observar? \n" +
                    "Las expresiones vocales y gestuales del niño/a en relación al juego que realiza su madre con el títere, intentando esta hacerle cosquillas y permitir un momento placentero de alegría al bebé.","Destacado")

            ,Contenido("Veo, veo ¿qué ves?","3","Interacción y Comprensión del Entorno","Exploración del Entorno Natural ","Sala Cuna", "Reconocer algunos elementos representativos de su entorno natural, tales como: animales, plantas, ríos, cerros, desierto.","qGMY3PEbGE0","En esta actividad los niños podrán visualizar y diferenciar los seres vivos del reino animal.","¿Qué materiales usar? \n" +
                    " Fotos captadas en su entorno natural cercano de algunos animales como: Perro, gato, pájaros (paloma, tregil, codorniz, aguilucho), u otros cercanos. Videos breves de imágenes de los animales de su entorno natural cercano. Lápices y hoja\n" +
                    " ¿Qué hace el adulto? \n" +
                    " Previo a la experiencia debe sacar fotografías y grabar videos de animales de su entorno natural cercano utilizando su celular. Luego el adulto invita al niño/a a sentarse en el sillón para mostrarle algo que tiene en su celular. Antes de invitar al niño/a, debe mantener el living ordenado y sin la televisión encendida, para evitar distracciones. El adulto le dirá al niño/a que le quiere mostrar unas fotos que tomó y se las muestra una a una preguntando ¿Qué animal es?, ¿Lo conoces?. Lo invita a ver los videos y escucha sus respuestas, finalmente lo invita a dibujar el animal favorito.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    " Disfrutará de la experiencia, observando las fotografías y videos de animales de su entorno cercano, nombrándolos, realizando su sonido, imitando sus movimientos y dibujando lo que más le gustó.\n" +
                    "¿Qué debo observar? \n" +
                    "Que el niño/a logre reconocer los animales que se le presentan en las fotografías y videos, dentro de sus posibilidades de lenguaje verbal y corporal.","Destacado")

            ,Contenido("JUGANDO CON LA LLUVIA","3","Interacción y Comprensión del Entorno","Exploración del Entorno Natural ","Sala Cuna", "Manifestar curiosidad y asombro por algunos elementos, situaciones y fenómenos que ocurren en su entorno natural cercano, tales como: arena, lluvia, viento, entre otros","_xdf4kU6RrE","En esta actividad los niños podrán interactuar con el fenómeno natural de la lluvia.","¿Qué materiales usar? \n" +
                    "Paraguas, ropa de abrigo (parka y calzado adecuado). Un día lluvioso para poder realizar la actividad.\n" +
                    " ¿Qué hace el adulto? \n" +
                    " El adulto debe estar atento a las condiciones del clima y aprovechar un día de lluvia para salir junto a sus hijo/a al patio o a la ventana a ver la lluvia. Si no hay cuarentena, puede salir al patio o fuera de casa a disfrutar de la lluvia cuando recién comienza en un paseo cercano al hogar. El adulto media con preguntas y/o afirmaciones como: ¿Conoces la lluvia?, ¡Mira, siente como cae en el rostro!, ¡saltemos los charcos para no mojarnos!, ¿Qué color tiene el cielo? ¿Te gustó el paseo? El adulto escucha sus respuestas.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    " El niño y la niña observará la naturaleza durante el paseo o salida al patio, jugando con su madre a saltar los charcos. Observando y comentando los cambios que observa como: día más oscuro, el color del cielo, la temperatura, entre otros.\n" +
                    "¿Qué debo observar? \n" +
                    "Las expresiones verbales y no verbales del niño/a en relación a la lluvia. Observar su agrado por la experiencia en la naturaleza.","Destacado")

            ,Contenido("Hacer Caballo de Palo con Calcetín","3","Desarrollo Personal y Social","Identidad y autonomía ","Medio", "Representar sus pensamientos y experiencias, atribuyendo significados a objetos o elementos de su entorno, usando la imaginación en situaciones de juego","OUA8T2IPIsM","En esta actividad los niños podrán elaborar un caballo de palo junto a sus padres","¿Qué materiales usar? \n" +
                    " Calcetín viejo, restos de tela para el relleno, 2 botones, hilo aguja palo de escoba viejo. Pegamento.\n" +
                    "¿Qué hace el adulto? \n" +
                    "El adulto armara la cabeza de caballo con un calcetín viejo relleno con mopa o trozos de tela y la pegara a un palo de escoba amarrándolo o pegándolo. Luego invitara al niño/a a decorarlo pegándole los ojos con botones, las clines con trozos de hilo y cociéndole una boca bordada, luego les pondrán una rienda amarrada y el niño/a podrá jugar por la casa jugando en el caballito de palo.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    " El niño/a junto al adulto, confeccionará el caballito de palo, armándolo y luego podrá jugar por la casa andando en el caballito de palo.\n" +
                    "¿Qué debo observar? \n" +
                    "El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Qué te parece el juguete que hicimos?, ¿Qué materiales usaste?, ¿Cómo se llama tu caballo?, ¿Dónde iras en tu caballito de palo? ","Destacado")

            ,Contenido("Historia Compartida","3","Comunicación Integral","Lenguaje Verbal ","Medio", "Manifestar interés por descubrir el contenido de textos, a través de la, la escucha atenta, la expresión de ideas y la formulación de preguntas.","2x_Kr_EH7b0","En esta actividad los niños podrán practicar su lenguaje verbal.","¿Qué materiales usar? \n" +
                    " Jugar con toda la familia\n" +
                    " ¿Qué hace el adulto? \n" +
                    " El adulto invitará al niño/a a jugar haciendo una historia compartida dando como elemento central un personaje ejemplo: había una vez un sapito verde que nadaba en la laguna…. y cada uno de los integrantes de la familia seguirá agregando algo a la historia hasta terminarla.\n" +
                    " ¿Qué hace el niño/a? \n" +
                    " El niño/a participara agregando mas contenido a la historia, con la participación de su familia\n" +
                    "¿Qué debo observar? \n" +
                    "El adulto junto al niño puede evaluar el trabajo con preguntas tales como: ¿Qué es lo que más te gusto de la historia?, ¿Qué parte de la historia inventaste tu?, ¿Qué parte de la historia invento tu hermano?, ¿Cómo termino la historia?","Destacado")

            ,Contenido("Hacer barquitos de papel y jugar en fuente de agua","3","Interacción y Comprensión \n" + " del Entorno","Pensamiento Matemático ","Medio", "Experimentar con materiales cotidianos tales como: agua y aire describiendo lo observado.","Laj6AuGri2w","En esta actividad los niños podrán elavorar un barco de papel y luego descubrir los principios de flotabilidad","¿Qué materiales usar? \n" +
                    " Una carta de naipe. Un vaso de vidrio lleno de agua hasta el borde y un recipiente para contener el agua (por si el experimento falle) \n" +
                    " ¿Qué hace el adulto? \n" +
                    " El adulto invita al niño/a a hacer un experimento motivándolo con la siguiente pregunta: ¿Cómo podemos voltear un vaso lleno de agua sin que se caiga el agua? Invitándolo a llenar un vaso de vidrio con agua hasta el borde y depositar el vaso en una fuente que pueda retener el agua por si el experimento falla, luego invitara al niño/a a poner la carta de naipe sobre el vaso y a dar vuelta el vaso viendo que ocurre. Preguntando al niño/a por que ocurre lo que ocurre. \n" +
                    "Explicación científica: sobre la carta actúan dos fuerzas, el peso del agua y la presión atmosférica, la presión atmosférica es mayor y empuja la carta hacia arriba. Aunque por poco si movemos la carta segura se cae. \n" +
                    " ¿Qué hace el niño/a? \n" +
                    " El niño/a seguirá las instrucciones del adulto y luego se preguntarán por qué ocurre eso. Dando posibles respuestas a las interrogantes del adulto \n" +
                    " ¿Qué debo observar? \n" +
                    "El adulto junto al niño /a puede evaluar el trabajo con preguntas tales como: ¿Por qué ocurre eso?, buscando posibles respuestas. Luego hablaran si les gusto la experiencia si le gustaría hacer nuevos experimentos otro día. ","Destacado")

            ,Contenido("Ver video de Cuento Monstruo Triste Monstruo Feliz","2","Desarrollo Personal y Social","Identidad y Autonomía","Medio", "Reconocer en sí mismo, en otras personas y en personajes de cuentos, emociones tales como: tristeza, miedo, alegría, pena y rabia.","IuEYPS9vTak","En esta actividad los niños podrán desarrollar sus habilidades de crecimiento personal e identidad","¿Qué materiales usar? \n" +
                    " Video del cuento monstruo tiste monstruo feliz. https://www.youtube.com/watch?v=B9YMaeehOmk Materiales para el dibujo\n" +
                    " ¿Qué hace el adulto? \n" +
                    "El adulto invita al niño/a a ver un video del cuento “Monstruo Triste Monstruo feliz”, luego de ver el video conversaran en torno a las cosas que lo hacen ponerse triste, las cosas que lo hacen poner enojado, las cosas que lo hacen sentir cariñoso, las cosas que lo hacen sentir triste, las cosas que lo hacen sentir miedo, las cosas que lo hacen sentir furioso, las cosas que lo hacen sentir divertido. \n" +
                    "¿Qué hace el niño/a? \n" +
                    " El niño/a conversara sobre las cosas que lo hacen feliz, triste, enojado, cariñoso, miedoso, furioso y divertido. Luego si tiene lápices de colores o\n" +
                    "tempera, podrá hacer una máscara del monstruo de la emoción que siente en ese momento contándole a su familia por qué se siente así.\n" +
                    " ¿Qué debo observar? \n" +
                    "El adulto junto al niño/a puede evaluar el trabajo con preguntas tales como: ¿Cómo te sientes hoy ?, ¿Cómo te sientes con mayor frecuencia?,\n" +
                    "¿Por qué te sientes así?\n","Destacado")

            ,Contenido("Experimento Vaso de Agua y Carta de Naipe","3","Interacción y Comprensión \n" + " del Entorno","Exploración del Entorno Natural","Medio", "Experimentar con materiales cotidianos tales como: agua y aire describiendo lo observado.","g615EoOIlug","En esta actividad los niños podrán aprender como funcionan las fuerzas de gravedad y tensión superficial del agua","¿Qué materiales usar? \n" +
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

        bundle = intent.extras                               // Variable que rescata los extras que trae el Intent
        email = bundle?.getString("email")              // Variable que rescata el correo
        provider = bundle?.getString("provider")        // Variable que rescata el provider

        prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
    }

    override fun onBackPressed() {
        intent = Intent(this, MainActivity::class.java)
        intent.putExtras(bundle!!)
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_logout -> {
                cerrarSesion(email, provider, true, prefs)
                val home = Intent(this, Login::class.java)
                startActivity(home)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}