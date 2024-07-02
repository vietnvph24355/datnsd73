module.exports = {
  printWidth: 100,
  singleQuote: true,
  trailingComma: 'all',
  overrides: [
    {
      files: ['docs/**/*.md', 'docs/src/pages/**/*.{js,tsx}', 'docs/data/**/*.{js,tsx}'],
      options: {
        printWidth: 100,
      },
    },
    {
      files: ['docs/pages/blog/**/*.md'],
      options: {
        printWidth: 82,
      },
    },
  ],

};
