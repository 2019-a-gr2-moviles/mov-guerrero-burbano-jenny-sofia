/**
 * Empresa.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    nombre: {
      type: 'string'
    },
    usuariosDeEmpresa: {//Nombre atributo de la realción
      collection: 'usuario', //Nombre del modelo a relaionar
      via: 'fkEmpresa' //Nombre atributo FK del otro modelo
    }

  },

};

