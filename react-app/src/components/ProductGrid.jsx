import PropTypes from "prop-types"
import { ProductDetail } from "./ProductDetail"

export const ProductGrid = ({handlerProductSelected, handlerRemove, products = []}) => {

    return (
        <table className="table table-hover table-striped">
            <thead>
                <tr>
                    <th>name</th>
                    <th>price</th>
                    <th>description</th>
                    <th>remove</th>
                    <th>update</th>
                </tr>
            </thead>
            <tbody>
                {products.map(product => {
                    return <ProductDetail handlerRemove={handlerRemove} product={product} key={product.name} handlerProductSelected={handlerProductSelected} />
                })}
            </tbody>
        </table>
    )

}

ProductGrid.propTypes = {
    products: PropTypes.array.isRequired,
    handlerRemove: PropTypes.func.isRequired,
    handlerProductSelected: PropTypes.func.isRequired
}