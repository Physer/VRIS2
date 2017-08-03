using System.ComponentModel.DataAnnotations;
using System.Linq;
using Microsoft.AspNetCore.Mvc.Controllers;
using Swashbuckle.AspNetCore.Swagger;
using Swashbuckle.AspNetCore.SwaggerGen;

namespace VRIS.API.OperationFilters
{
    /// <summary>
    /// <see cref="IOperationFilter"/> that fixed the fact that swagger seems to ignore the <see cref="RequiredAttribute"/> for request bodies
    /// </summary>
    public sealed class FixRequiredFromBodyOperationFilter : IOperationFilter
    {
        /// <inheritdoc cref="IOperationFilter"/>
        public void Apply(Operation operation, OperationFilterContext context)
        {
            if (operation.Parameters == null || !operation.Parameters.Any()) return;
            SetBodyParametersAsRequired(operation, context);
        }

        private static void SetBodyParametersAsRequired(Operation operation, OperationFilterContext context)
        {
            var bodyParameters = operation.Parameters
                .Where(p => p is BodyParameter);

            foreach (var bodyParameter in bodyParameters)
            {
                var parameter = context.ApiDescription.ActionDescriptor.Parameters
                    .FirstOrDefault(methodParam => methodParam.Name.Equals(bodyParameter.Name)) 
                    as ControllerParameterDescriptor;
                if(parameter == null) continue;
                // ReSharper disable once IsExpressionAlwaysFalse
                #pragma warning disable CS0184 // 'is' expression's given expression is never of the provided type
                // ReSharper disable once IsExpressionAlwaysFalse
                if (!parameter.ParameterInfo.CustomAttributes.Any(attr => attr is RequiredAttribute))
                    bodyParameter.Required = true;
            }
        }
    }
}
