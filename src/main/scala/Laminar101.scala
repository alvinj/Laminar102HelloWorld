package alvin

import com.raquo.laminar.api.L._
import org.scalajs.dom

object Laminar101Reactive {

    def main(args: Array[String]): Unit = {

        val nameVar = Var(initial = "world")

        val rootElement = div(
            padding("2em"),
            div(
                backgroundColor("#e0e0e0"),
                label("Your name: "),
                input(
                    onMountFocus,
                    placeholder := "Enter your name here",
                    // as the user types into this 'input' field, send the contents
                    // of the input field to nameVar. nameVar is a reactive variable
                    // (observable), and it’s used to update the two DIVs below.
                    inContext { thisNode => onInput.map(_ => thisNode.ref.value) --> nameVar }
                ),
            ),
            div(
                backgroundColor("#d9d9d9"),
                "Reactive 1: ",
                // any time nameVar is updated, it sends us a signal, and this
                // field is updated:
                child.text <-- nameVar.signal
            ),
            div(
                backgroundColor("#d0d0d0"),
                // any time nameVar is updated, it sends us a signal, and this
                // field is updated. note in this case that the value is map’d:
                "Reactive 2: ",
                child.text <-- nameVar.signal.map(_.toUpperCase)
            )
        )

        // `#root` must match the `id` in index.html
        val containerNode = dom.document.querySelector("#root")

        // render the element in the container
        render(containerNode, rootElement)
    }

}




