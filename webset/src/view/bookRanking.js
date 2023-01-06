import React from 'react';
import {AdministratorHeadOfPage} from "../components/head"
import {BookRankingList} from "../components/bookRanking";
import {Button, DatePicker, Radio, Space} from 'antd';


export const books_list=[]

export class BookRanking extends React.Component{
    constructor(props) {
        super(props);
        this.state={
            books:props.books,
            size:1
        }
    }



    componentWillMount(){
        if(localStorage.getItem("id")==null){
            alert("please log in first")
            window.location.replace("http://localhost:3000/?#/");
            return;
        }
        fetch("http://localhost:8080/checkAdministrator?userid="+localStorage.getItem("id"))
            .then(response => response.json())
            .then(data => {
                if(data==="no"){
                    alert("Non-administrators do not have permission to access this page")
                }
                else {
                    fetch("http://localhost:8080/bookranking")
                        .then(response => response.json())
                        .then(data => {
                            this.setState({
                                books:data,
                            })
                        }).catch(function (ex) {
                        console.log('parsing failed', ex)
                    })
                }
            }).catch(function (ex) {
            console.log('parsing failed', ex)
        })

    }

    handleSizeChange = e => {
        this.setState({ size: e.target.value } );
        if(e.target.value===1){
            fetch("http://localhost:8080/bookranking")
                .then(response => response.json())
                .then(data => {
                    this.setState({
                        books:data,
                    })
                }).catch(function (ex) {
                console.log('parsing failed', ex)
            })
        }
        else if(e.target.value===2){
            fetch("http://localhost:8080/bookrankingweek")
                .then(response => response.json())
                .then(data => {
                    this.setState({
                        books:data,
                    })
                }).catch(function (ex) {
                console.log('parsing failed', ex)
            })
        }
        else if(e.target.value===3){
            fetch("http://localhost:8080/bookrankingmonth")
                .then(response => response.json())
                .then(data => {
                    this.setState({
                        books:data,
                    })
                }).catch(function (ex) {
                console.log('parsing failed', ex)
            })
        }
    };

    render() {
        const { size } = this.state.size;
        const { RangePicker } = DatePicker;

        return (
            <div id="wrapper">
                <div className="wrapper-holder">
                    <AdministratorHeadOfPage/>
                    {/*<div >*/}
                        {/*<DistributionMapOfBookSales  />*/}
                    {/*</div>*/}
                    <section className="main">
                        <div className="content">
                            <Radio.Group value={size} onChange={this.handleSizeChange}>
                                <Radio.Button value={1}>All</Radio.Button>
                                <Radio.Button value={2}>This Week</Radio.Button>
                                <Radio.Button value={3}>This month</Radio.Button>
                            </Radio.Group>
                            <Space direction="vertical" size={12}>
                                <RangePicker onChange={(dateStrings: [string, string])=>{
                                    if(dateStrings===null||dateStrings[0]===null||dateStrings[1]===null)
                                        return;
                                    let begin_date = new Date(dateStrings[0]),
                                        month1 = '' + (begin_date.getMonth() + 1),
                                        day1 = '' + begin_date.getDate(),
                                        year1 = begin_date.getFullYear();
                                    if (month1.length < 2) month1 = '0' + month1;
                                    if (day1.length < 2) day1 = '0' + day1;
                                    let begin=[year1, month1, day1].join( '.' );

                                    let end_date = new Date(dateStrings[1]),
                                        month2 = '' + (end_date.getMonth() + 1),
                                        day2 = '' + end_date.getDate(),
                                        year2 = end_date.getFullYear();

                                    if (month2.length < 2) month2 = '0' + month2;
                                    if (day2.length < 2) day2 = '0' + day2;

                                    let end=[year2, month2, day2].join( '.' );
                                    fetch("http://localhost:8080/bookrankingperiod?begin="+begin+"&end="+end)
                                        .then(response => response.json())
                                        .then(data => {
                                            this.setState({
                                                books:data,
                                            })
                                        }).catch(function (ex) {
                                        console.log('parsing failed', ex)
                                    })
                                }
                                }/>
                            </Space>
                            <BookRankingList books={this.state.books}/>
                            <ul className="list-table" style={{display: "none"}}>
                            </ul>
                        </div>
                    </section>
                </div>
            </div>
        );
    }
}

function BookRankingPage(){
    return(<BookRanking books={books_list}/>

    );
}
export default BookRankingPage;