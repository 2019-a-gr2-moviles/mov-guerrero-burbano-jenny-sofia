/**
 * Materia.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    nombre: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 60,
    },
    codigo: {
      type: 'string',
      required: true,
    },
    descripcion: {
      type: 'string',
      required: true,
    },
    activo: {
      type: 'boolean',
      required: true,
    },
    fechaCreacion: {
      type: 'string',
      required: true
    },
    numeroHorasPorSemana: {
      type: 'number',
      required: true
    },
    latitud: {
      type: 'string'
    },
    longitud: {
      type: 'string'
    },
    estudianteId: {
      model:'estudiante'
    }

  },

};

