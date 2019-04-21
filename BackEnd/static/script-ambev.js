'use strict'

//Smooth scroll
$(document).ready(function() {
  $('a[href^="#"]').on('click', function(e) {
    e.preventDefault()

    var target = this.hash
    var $target = $(target)

    $('html, body').animate(
      {
        scrollTop: $target.offset().top - 30
      },
      1000,
      'swing'
    )
  })

  $('#see-challenges').on('click', function(e) {
    e.preventDefault()
    var $target = $('#challenges')

    $('html, body').animate(
      { scrollTop: $target.offset().top - 30 },
      1000,
      'swing'
    )
  })

  $('.register-button').on('click', function(e) {
    e.preventDefault()
    var win = window.open('/register', '_blank')
    win.focus()
  })

  $('#question-form').submit(function() {
    $('#contact-btn-text').hide()
    $('#contact-loading').css('display', 'inline-block')
    $.ajax({
      type: 'POST',
      url: $(this).attr('action'),
      data: $(this).serialize(),
      statusCode: {
        201: function() {
          $('#question-success-modal').modal('show')
          $('#contact-btn-text').show()
          $('#contact-loading').hide()
        }
      }
    })

    return false
  })

  $('#register-form').submit(function() {
    $('#register-btn-text').hide()
    $('#register-loading').css('display', 'inline-block')
    $.ajax({
      type: 'POST',
      url: $(this).attr('action'),
      data: $(this).serialize(),
      statusCode: {
        201: function() {
          $('#register-modal').modal('hide')
          $('#register-success-modal').modal('show')
          $('#register-btn-text').show()
          $('#register-loading').hide()
        }
      }
    })

    return false
  })
})
