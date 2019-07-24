/**
 * Estudiante.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    nombres: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 60,
    },
    apellidos: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 60,
    },
    fechaNacimiento: {
      type: 'string',
      required: true,
    },
    semestreActual: {
      type: 'number',
      required: true,
    },
    graduado: {
      type: 'boolean',
      required: true
    },
    materiaDeEstudiante: {//Nombre atributo de la realción
      collection: 'materia', //Nombre del modelo a relaionar
      via: 'estudianteId' //Nombre atributo FK del otro modelo
    },

  },

};

