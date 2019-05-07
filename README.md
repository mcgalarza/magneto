# Magneto

Magneto es una API REST que determina si un humano es mutante basándose en su secuencia ADN.

## Ejecución
Para poder ejecutar la aplicación de manera local, es necesario contar con `docker`.

Clonar el repositorio: `git clone https://github.com/mcgalarza/magneto`

Dentro del repositorio, ejecutar:
```bash
gradle build
docker build -t magneto .
docker-compose up
```
La aplicación se iniciará en el puerto 8081

Además, la API se encuentra hosteada en Heroku. Utilizando los plugins de Heroku de postgres y redis. 

Aclaración: la solución de mensajería utilizando colas no se llegó a deployar en Heroku, este feature solo se podrá ver ejecutando la aplicación de manera local.

## Ejemplo de uso

### POST /mutant

**Header:** Content-type Application/json

**Body**
```
{
  "dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
```

**Responses**

**200 OK** Si corresponde a un mutante

**403 Forbidden** Si corresponde a un humano no mutante

**400 Bad Request** Si los parámetros son inválidos

**Local:** [http://localhost:8081/mutant](http://localhost:8081/mutant)

**Heroku:** [https://magnetodna.herokuapp.com/mutant](https://magnetodna.herokuapp.com/mutant)

### GET /stats

**Header:** Content-type Application/json

**Response Body**
```bash
{
  "countMutantDna": 40
  "countHumanDna": 100
  "ratio": 0.4
}
```
**Local:** [http://localhost:8081/stats](http://localhost:8081/stats)

**Heroku:** [https://magnetodna.herokuapp.com/stats](https://magnetodna.herokuapp.com/stats)

