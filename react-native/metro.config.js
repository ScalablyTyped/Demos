module.exports = {
  transformer: {
    babelTransformerPath: require("path").resolve("./fastopt-noparse.js"),
    getTransformOptions: async () => ({
      transform: {
        experimentalImportSupport: false,
        inlineRequires: false,
      },
    }),
  },
};
