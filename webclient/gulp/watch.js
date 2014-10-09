'use strict';

var gulp = require('gulp');

gulp.task('watch', ['wiredep'] ,function () {
  gulp.watch('src/{app,components}/**/*.js', ['scripts']);
  gulp.watch('bower.json', ['wiredep']);
});
