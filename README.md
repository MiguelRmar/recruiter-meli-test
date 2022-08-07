# recruiter-mutants
 recruiter magneto mutants

Este es un programa para ayudar a Magneto a reclutar mutantes, para ello se creo una app con un método que detecta mutantes
el método es llamado "isMutant" y nos dice si la persona es mutante o no.

la aplicación se encuentra alojada en un servicio de Elastic Beanstalk de AWS con la siguiente dirección:
 ### `Recruitermutants-env.eba-ndbzkj9t.us-east-1.elasticbeanstalk.com`

Para lograr consumir los servicios que la aplicación expone está habilitado un API Rest con los siguientes métodos:

### `/http://recruitermutants-env.eba-ndbzkj9t.us-east-1.elasticbeanstalk.com/api/mutant`
Este método ayuda a validar si una persona es mutante, el cual devuelve HTTP 200-OK cuando lo es, en caso contrario devuelve HTTP 403-Forbidden

### `http://recruitermutants-env.eba-ndbzkj9t.us-east-1.elasticbeanstalk.com/api/stats`
Este método devuelve unas estadisticas de cuantas personas que hemos validado son mutantes, cuantas humanos y una relación entre ellas. 

Para lograr guardar los registros y asi mostrar las estadicitas se aprobicionó una base de datos en RDS de AWS.

La dirección de la base de datos es: 
### `magneto-dnas.cewr9tyllzpo.us-east-1.rds.amazonaws.com`
