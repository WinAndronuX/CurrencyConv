## CurrencyConv

![CurrencyConv screenshot](https://raw.githubusercontent.com/WinAndronuX/CurrencyConv/master/screenshots/screenshot_1.png)

### Introducccion

**CurrencyConv** es una herramienta CLI para convertir monedas usando la API de [Exchange Rate API](https://www.exchangerate-api.com/).

Puedes saber más sobre las monedas soportadas en: [Supported Currencies](https://www.exchangerate-api.com/docs/supported-currencies).

Este proyecto es la resolución de: 
> Conversor de Moneda - Challenge ONE - Java - Back end

### Requerimientos

 - Java >= 17

### Instalación y ejecución
Ve a [Release v1.1](https://github.com/WinAndronuX/CurrencyConv/releases/tag/v1.1) y descarga CurrencyConv.jar

Abre una terminal/linea de comandos  y ejecuta:

`java  -jar  CurrencyConv.jar`

### Comandos

 - `conv`: Convierte el monto de una moneda a otra o de una moneda a varias
   - Syntaxis: `conv [amount] [base_currency] to [targets_currency]`
     - `amount`: Cualquier numero positivo
     - `base_currency`: Cualquier currency code valido -> [Supported Currencies](https://www.exchangerate-api.com/docs/supported-currencies)
     - `targets_currency`: Uno o varios currency codes válidos separados por `","`
 - `supported`: Muestra una lista de todos los currency codes soportados
 - `search`: Return all currencies matching the query
    - Usage: `search [query]`
      - Examples:
        - `search Peso` -> Returns all currencies named "pesos"
        - `search Peruvian` -> Returns the currency used in Peru
      - Tip: For country names use their demonym
        - Mexico -> Mexican
        - Argentina -> Argentine
- `version`: Shows the program version
- `clear`: Cleans the screen
 - `quit, q`: Salir del programa
 - `help, ?`: Muestra un mensaje de ayuda

### Ejemplos de Uso

 - `conv 1 USD to MXN`
 - `conv 1 USD to ARS,COP,CLP,PEN`

### Acerca de mi

Soy Luis Daniel Salazar Sanchez, estudiante de Ciencias Computacionales en la Universidad Autonoma de Nuevo Leon, ademas me encuentro participando en grupo G6 de Alura ONE en la especializacion de backend.

Mis redes

 - [LinkedIn](www.linkedin.com/in/ldanielsalazars)

