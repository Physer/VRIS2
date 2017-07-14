using System;
using System.Collections.Generic;
using System.Net;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;

namespace VRIS.Business.HttpFilters
{
    /// <summary>
    /// Filter to handle exception data
    /// </summary>
    public class GlobalExceptionFilter : IExceptionFilter
    {
        private readonly bool _showStackTrace;

        /// <summary>
        /// Filter to handle exception data
        /// </summary>
        /// <param name="showStackTrace"></param>
        public GlobalExceptionFilter(bool? showStackTrace = false)
        {
            _showStackTrace = showStackTrace ?? false;
        }

        /// <inheritdoc cref="IExceptionFilter"/>
        public void OnException(ExceptionContext context) => context.Result = FilterExceptions(context);

        private IActionResult WriteResponse(HttpStatusCode statusCode, Exception exception)
            => WriteResponse((int) statusCode, exception);

        private IActionResult WriteResponse(WebExceptionStatus statusCode, Exception exception)
            => WriteResponse((int) statusCode, exception);

        private IActionResult WriteResponse(int statusCode, Exception exception) 
            => new JsonResult(new
            {
                Exception = exception.GetType().FullName,
                Data = _showStackTrace ? exception.Data : null,

                exception.Message
            })
            {
                StatusCode = statusCode
            };

        private IActionResult FilterExceptions(ExceptionContext context)
        {
            if (context.Exception is WebException)
                return WriteResponse(((WebException) context.Exception).Status, context.Exception);
            if (context.Exception is UnauthorizedAccessException)
                return WriteResponse(HttpStatusCode.Unauthorized, context.Exception);
            if (context.Exception is NotImplementedException)
                return WriteResponse(HttpStatusCode.NotImplemented, context.Exception);
            if (context.Exception is KeyNotFoundException)
                return WriteResponse(HttpStatusCode.NotFound, context.Exception);
            if (context.Exception is ArgumentException)
                return WriteResponse(HttpStatusCode.BadRequest, context.Exception);

            return WriteResponse(HttpStatusCode.InternalServerError, context.Exception);
        }
    }
}
