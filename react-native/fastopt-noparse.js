var transformer = require('metro-react-native-babel-transformer')

module.exports.transform = function ({ src, filename, options }) {
  options = options || {};
  if (filename.indexOf('fastopt') > -1) {
    return {
      code: src,
      filename
    }
  }
  else {
    return transformer.transform({ src, filename, options });
  }
}
