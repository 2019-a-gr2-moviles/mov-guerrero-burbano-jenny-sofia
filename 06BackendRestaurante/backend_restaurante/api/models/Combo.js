/**
 * Combo.js
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
    descripcion: {
      type: 'string',
      required: true,
      maxLength: 300
    },
    precio: {
      type: 'number',
      required: true
    },
    platosCombo: {
      collection: 'historialComboPlato',
      via: 'idCombo'
    }

  },

};

